package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.CartDetail;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail , Long> {

    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkExist " +
            "FROM cart where customer_id =?1 limit 1", nativeQuery = true)
    boolean isCartExist(Long customerId);

    List<CartDetail> findByCart(Cart cart);

    Optional<CartDetail> findOneByProductAndSizeAndCart(Product product , String size , Cart cart);

}
