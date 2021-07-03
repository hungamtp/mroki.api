package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

//    @Query(value = "SELECT o From Orders WHERE o.isShopping = true and ")
//    Optional<Orders> findShoppingCart(Long userId );

}
