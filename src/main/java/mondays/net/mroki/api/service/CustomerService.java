package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.authDTO.EmailDTO;
import mondays.net.mroki.api.dto.authDTO.LoginDTO;
import mondays.net.mroki.api.dto.customerDTO.CustomerUpdateDTO;
import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface CustomerService {

    void save(Customer customer);

    PageDTO getAlLCustomer(Pageable pageable , Specification specification);

    Customer findByUsername(String username);

    boolean findByEmail(String email);

    boolean updatePassword(EmailDTO user);
    void activateDeactivate(Long customerId , boolean active);
    boolean updateCustomer(CustomerUpdateDTO dto);
}
