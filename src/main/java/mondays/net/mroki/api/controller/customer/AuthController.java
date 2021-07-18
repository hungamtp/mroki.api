package mondays.net.mroki.api.controller.customer;


import io.jsonwebtoken.Jwts;
import mondays.net.mroki.api.dto.LoginDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.SignupDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Role;
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
                    .role(Role.builder().id("U").build())
                    .isVerifiedEmail(false)
                    .build();

            customerService.save(customer);
            response.setSuccessCode(SuccessCode.SIGN_UP_SUCCESS);
        } catch (RuntimeException ex) {
            response.setErrorCode(ErrorCode.SIGN_UP_FAILED);
        }
        return ResponseEntity.ok().body(response);

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

                response.setSuccessCode(SuccessCode.LOGIN);

                String token = Jwts.builder()
                        .setSubject(authenticate.getName())
                        .claim("authorities", authenticate.getAuthorities())
                        .setIssuedAt(new Date())
                        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                        .signWith(secretKey)
                        .compact();
                response.setData("Bearer " + token);

                return ResponseEntity.ok().body(response);

            } else {

                response.setErrorCode(ErrorCode.LOGIN);
                return ResponseEntity.badRequest().body(response);

            }

        } catch (AuthenticationException ex) {
            response.setErrorCode(ErrorCode.WRONG_USERNAME_OR_PASSWORD);
            return ResponseEntity.badRequest().body(response);
        }


    }


}
