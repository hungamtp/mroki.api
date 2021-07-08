package mondays.net.mroki.api.service.impl;

import mondays.net.mroki.api.dto.UserDetail;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = userRepo.findCustomerByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserDetailService: No username found");
        }

        return new UserDetail(user);
    }
}
