package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findOrdersByCustomer(Customer customer);



}
