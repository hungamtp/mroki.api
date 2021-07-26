package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>  , JpaSpecificationExecutor<Customer> {

    Customer findByUsername(String username);

    @Query(value = "SELECT id , username , avatar , phone , email , role_id , is_verified_email" +
            " FROM customer WHERE role_id != 'A' LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Object[]> findAllCustomer(int limit, int offset);

    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkUserExist "+
            "FROM customer c WHERE c.username = ?1 LIMIT 1" , nativeQuery = true)
    boolean isExist(String username);
}
