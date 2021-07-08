package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUsername(String username);

    @Query(value = "SELECT id , username , avatar , phone , email , role_id , is_verified_email" +
            " FROM customer WHERE role_id != 'A' LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Object[]> findAllCustomer(int limit, int offset);

}
