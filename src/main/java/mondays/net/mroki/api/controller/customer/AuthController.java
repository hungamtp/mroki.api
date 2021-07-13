package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.SignupDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Role;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("account/")
@AllArgsConstructor
public class AuthController {


    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final CustomerServiceImpl customerService;

    @PostMapping("/signup")
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


}
