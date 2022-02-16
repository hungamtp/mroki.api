package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>  , JpaSpecificationExecutor<Customer> {

    Customer findByUsername(String username);
    Optional<Customer> findCustomerByEmail(String email);

    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkUserExist "+
            "FROM customer c WHERE c.username = ?1 LIMIT 1" , nativeQuery = true)
    boolean isExist(String username);

    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkUserExist "+
            "FROM customer c WHERE c.email = ?1 LIMIT 1" , nativeQuery = true)
    boolean isEmailExist(String email);
}
