package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.customerDTO.CustomerDTO;
import mondays.net.mroki.api.dto.customerDTO.RoleDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.filter.CustomerSpecificationBuilder;
import mondays.net.mroki.api.filter.ProductSpecificationsBuilder;
import mondays.net.mroki.api.service.CustomerService;
import mondays.net.mroki.api.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
@RequestMapping("admin/user")
@CrossOrigin
@PreAuthorize("hasRole('Admin')")
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
    public ResponseEntity<ResponseDTO> getAllUser(@RequestParam Integer page,
                                                     @RequestParam Integer size,
                                                     @RequestParam String sort,
                                                     @RequestParam String search) {
        ResponseDTO response = new ResponseDTO();

        if (!Optional.ofNullable(sort).isPresent()) sort = "id";
        Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), size, Sort.by(sort));

        CustomerSpecificationBuilder builder = new CustomerSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Customer> spec = builder.build();


        response.setData(customerService.getAlLCustomer(pageable  , spec));
        return ResponseEntity.ok().body(response);
    }

}
