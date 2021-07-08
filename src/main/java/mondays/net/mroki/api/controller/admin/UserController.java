package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CustomerDTO;
import mondays.net.mroki.api.dto.RoleDTO;
import mondays.net.mroki.api.service.impl.CustomerServiceImpl;
import mondays.net.mroki.api.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    private final CustomerServiceImpl customerService;

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
