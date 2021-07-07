package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.LoginRequest;
import mondays.net.mroki.api.dto.SignupRequest;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Role;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("account/")
@AllArgsConstructor
public class CustomerController {


    private final PasswordEncoder passwordEncoder ;
    @Autowired
    private final CustomerServiceImpl customerService;

    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody SignupRequest user){

        Customer  customer = Customer.builder()
                .username(user.getUsername())
                .password("{bcrypt}"+passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(Role.builder().id("U").build())
                .build();

        customerService.save(customer);
    }

//    @PostMapping("resetPassword")
//    public String forgetPassword(@RequestBody LoginRequest user){
//        return "password";
//    }

}
