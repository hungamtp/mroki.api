package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.UsernamePassword;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Role;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
@AllArgsConstructor
public class CustomerController {


    private final PasswordEncoder passwordEncoder ;
    @Autowired
    private final CustomerServiceImpl customerService;

    @PostMapping
    public void signUp(@RequestBody UsernamePassword user){

        Customer  customer = Customer.builder()
                .username(user.getUsername())
                .password("{bcrypt}"+passwordEncoder.encode(user.getPassword()))
                .role(Role.builder().id("U").build())
                .build();

        customerService.save(customer);
    }

}
