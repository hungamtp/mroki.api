package mondays.net.mroki.api.controller.customer;


import io.jsonwebtoken.Jwts;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.authDTO.EmailDTO;
import mondays.net.mroki.api.dto.authDTO.LoginDTO;
import mondays.net.mroki.api.dto.authDTO.LoginResponseDTO;
import mondays.net.mroki.api.dto.authDTO.SignupDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Role;
import mondays.net.mroki.api.exception.DuplicatedDataException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.security.jwt.JwtConfig;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("auth/")
@CrossOrigin
public class AuthController {


    public AuthController(AuthenticationManager authenticationManager,
                          JwtConfig jwtConfig,
                          SecretKey secretKey,
                          PasswordEncoder passwordEncoder,
                          CustomerServiceImpl customerService) {

        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;

    }

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final CustomerServiceImpl customerService;

    @PostMapping("signup")
    public ResponseEntity<ResponseDTO> signUp(@Valid @RequestBody SignupDTO user) {
        ResponseDTO response = new ResponseDTO();

        try {
            Customer customer = Customer.builder()
                    .username(user.getUsername())
                    .password("{bcrypt}" + passwordEncoder.encode(user.getPassword()))
                    .email(user.getEmail())
                    .role(Role.builder().id(1L).build())
                    .phone(user.getPhone())
                    .isVerifiedEmail(false)
                    .build();

            customerService.save(customer);
            response.setSuccessCode(SuccessCode.SIGN_UP_SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (DuplicatedDataException ex) {
            response.setErrorCode(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }


    }

    @PostMapping("login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO user) {
        ResponseDTO response = new ResponseDTO();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );
        try {
            Authentication authenticate = authenticationManager.authenticate(authentication);
            if (authenticate.isAuthenticated()) {

                String token = Jwts.builder()
                        .setSubject(authenticate.getName())
                        .claim("authorities", authenticate.getAuthorities())
                        .setIssuedAt(new Date())
                        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                        .signWith(secretKey)
                        .compact();

                String defaultAvatar = "https://firebasestorage.googleapis.com/v0/b/timer-34f5a.appspot.com/" +
                        "o/avatar%2Favatar%20default.png?alt=media&token=b12a3df6-93f5-4662-8628-e34c94817c9f";
                Customer customer = customerService.findByUsername(user.getUsername());

                LoginResponseDTO loginResponse = LoginResponseDTO.builder()
                        .avatar(customer.getAvatar() == null || customer.getAvatar().equals("")
                                ? defaultAvatar : customer.getAvatar())
                        .username(customer.getUsername())
                        .role(customer.getRole().getRoleName())
                        .jwt("Bearer " + token)
                        .userId(customer.getId())
                        .isActive(customer.isActive())
                        .name(customer.getUsername())
                        .address(customer.getPhone())
                        .phone(customer.getPhone())
                        .build();

                response.setData(loginResponse);
                response.setSuccessCode(SuccessCode.LOGIN_SUCCESS);
                return ResponseEntity.ok().body(response);
            } else {

                response.setErrorCode(ErrorCode.LOGIN_FAIL);
                return ResponseEntity.ok().body(response);

            }

        } catch (AuthenticationException ex) {
            response.setErrorCode(ErrorCode.WRONG_USERNAME_OR_PASSWORD);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping("change-password")
    public ResponseEntity<ResponseDTO> changePassword(@Valid @RequestBody EmailDTO user) {
        ResponseDTO response = new ResponseDTO();
        try {
            if (customerService.updatePassword(user) == false) {
                response.setSuccessCode(SuccessCode.UPDATE_PASS);
                return ResponseEntity.ok().body(response);
            } else {
                response.setErrorCode(ErrorCode.UPDATE_PASS);
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.UPDATE_PASS);
            return ResponseEntity.ok().body(response);
        }
    }

}
