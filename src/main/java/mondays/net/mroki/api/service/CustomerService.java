package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.customerDTO.CustomerDTO;
import mondays.net.mroki.api.entity.Customer;

import java.util.List;


public interface CustomerService {

    void save(Customer customer);

    List<CustomerDTO> getAlLCustomer(int page);

    Customer findByUsername(String username);
}
