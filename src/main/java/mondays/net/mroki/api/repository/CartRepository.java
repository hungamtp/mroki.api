package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT id FROM cart WHERE customer_id =?1" , nativeQuery = true)
    Long getCartId (Long customerId);

    Cart findCartByCustomer(Customer customer);

    @Query(value = "SELECT product_id FROM cart_product WHERE product_id = ?1 AND cart_id = ?2" , nativeQuery = true)
    Long getProductIdInCart (Long productId , Long cartId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart_product(cart_id , product_id) VALUES(?1 , ?2)" , nativeQuery = true)
    void addProductToCart(Long cartId , Long productId);
}
