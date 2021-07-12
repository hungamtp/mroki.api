package mondays.net.mroki.api.service.impl;

import mondays.net.mroki.api.security.authentication.UserDetail;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.repository.CustomerRepository;
import mondays.net.mroki.api.security.authentication.UserDetailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;

class CustomerDetailServiceTest {

    @Mock
    private CustomerRepository repo;

    @InjectMocks
    private UserDetailService service;


    @Test
    void loadUserByUsername() {
        Customer customer = Customer.builder().username("username").build();

        UserDetail userDetail = new UserDetail(customer);

        when(repo.findCustomerByUsername("username")).thenReturn(customer);

        UserDetail customer1 = (UserDetail) service.loadUserByUsername("username");

        assertThat(customer1).isEqualTo(userDetail);
    }
}