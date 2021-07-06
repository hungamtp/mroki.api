package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Customer findCustomerByUsername(String username);

}
