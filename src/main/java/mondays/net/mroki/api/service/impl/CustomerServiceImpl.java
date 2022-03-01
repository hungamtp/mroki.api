package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CustomerConverter;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.authDTO.EmailDTO;
import mondays.net.mroki.api.dto.authDTO.LoginDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.exception.DuplicatedDataException;
import mondays.net.mroki.api.repository.CustomerRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private  final PasswordEncoder passwordEncoder;
    private final CustomerConverter converter;
    private final CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {

        Customer c = customerRepository.findByUsername(customer.getUsername());
        if (c != null)
            throw new DuplicatedDataException(ErrorCode.USERNAME_NOT_AVAILABLE);

        if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent())
            throw new DuplicatedDataException(ErrorCode.EMAIL_NOT_AVAILABLE);

        customerRepository.save(customer);


    }

    @Override
    public PageDTO getAlLCustomer(Pageable pageable, Specification specification) {

        return converter.entityToDto(customerRepository.findAll(specification, pageable));
    }


    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public boolean findByEmail(String email) {
        if(customerRepository.findByEmail(email) == null) return false;
        return true;
    }

    @Override
    public boolean updatePassword(EmailDTO user) {
        Customer customer = customerRepository.findByEmail(user.getEmail());
        if(customer == null) return false;
        customer.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        customerRepository.save(customer);
        return true;
    }
    public boolean isExist(String username) {
        return customerRepository.isExist(username);
    }

    public void activateDeactivate(Long customerId , boolean active){
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.orElseThrow(
                () -> new IllegalStateException(ErrorCode.USER_NOT_FOUND)
        );

        Customer customer1 = customer.get();
        customer1.setActive(active);
        customerRepository.save(customer1);
    }


}
