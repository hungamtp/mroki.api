package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.customerDTO.CustomerDTO;
import mondays.net.mroki.api.dto.customerDTO.RoleDTO;
import mondays.net.mroki.api.service.CustomerService;
import mondays.net.mroki.api.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("admin/user")
@CrossOrigin
public class UserController {

    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final RoleServiceImpl roleService;


    @GetMapping("/role")
    public List<RoleDTO> getAllRole() {
        return roleService.getAllRole();
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomer(@RequestParam(required = false) Optional<Integer> page) {

        return customerService.getAlLCustomer(page.orElse(0));

    }

}
