package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface CustomerService {

    void save(Customer customer);

    PageDTO getAlLCustomer(Pageable pageable , Specification specification);

    Customer findByUsername(String username);
}
