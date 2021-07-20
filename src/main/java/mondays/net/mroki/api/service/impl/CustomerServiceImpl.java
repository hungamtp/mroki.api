package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.customer.CustomerDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.exception.DuplicatedDataException;
import mondays.net.mroki.api.repository.CustomerRepository;
import mondays.net.mroki.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final int PAGE_SIZE = 30;

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {

        if (isExist(customer.getUsername()))
            throw new DuplicatedDataException("User name is exist");

        else
            customerRepository.save(customer);


    }

    @Override
    public List<CustomerDTO> getAlLCustomer(int page) {

        return convertCustomerToCustomerDTO(customerRepository.findAllCustomer(PAGE_SIZE, PAGE_SIZE * page));
    }

    List<CustomerDTO> convertCustomerToCustomerDTO(List<Object[]> customers) {

        List<CustomerDTO> result = new ArrayList<>();

        customers.stream().forEach((customer) -> {

            Long id = ((BigInteger) customer[0]).longValue();
            String username = (String) customer[1];
            String avatar = (String) customer[2];
            String phone = (String) customer[3];
            String email = (String) customer[4];
            String roleId = (String) customer[5];
            boolean isVerifiedEmail = Boolean.parseBoolean((String) customer[5]);

            result.add(new CustomerDTO(id, username, avatar, phone, email, roleId, true));
        });

        return result;
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public boolean isExist(String username) {
        return customerRepository.isExist(username);
    }


}
