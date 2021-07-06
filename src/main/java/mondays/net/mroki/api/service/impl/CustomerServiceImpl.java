package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.repository.CustomerRepository;
import mondays.net.mroki.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository  customerRepository;

    @Override
    public void save(Customer customer) {

        customerRepository.save(customer);

    }


}
