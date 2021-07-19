package mondays.net.mroki.api.security.authentication;

import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserDetailService: No username found");
        }

        return new UserDetail(user);
    }
}
