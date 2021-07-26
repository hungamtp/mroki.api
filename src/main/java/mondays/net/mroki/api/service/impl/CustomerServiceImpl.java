package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CustomerConverter;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.customerDTO.CustomerDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.exception.DuplicatedDataException;
import mondays.net.mroki.api.repository.CustomerRepository;
import mondays.net.mroki.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerConverter converter;

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
    public PageDTO getAlLCustomer(Pageable pageable , Specification specification) {

        return converter.entityToDto(customerRepository.findAll(specification, pageable));
    }



    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public boolean isExist(String username) {
        return customerRepository.isExist(username);
    }


}
