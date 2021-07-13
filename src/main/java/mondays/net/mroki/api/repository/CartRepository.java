package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomer(Customer customer);

    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkExist "+
            "FROM cart where customer_id =?1 limit 1" , nativeQuery = true)
    boolean isExist(Long customerId);

    @Query(value = "SELECT CASE WHEN count(products_id) > 0 THEN true ELSE false END checkExist "+
            "FROM product_cart where products_id = ?1 AND cart_id = ?2 LIMIT 1", nativeQuery = true)
    boolean isProductInCart(Long productId , Long cartId);

    @Query(value = "insert into product_cart values(?1 , ?2 , ?3)" , nativeQuery = true)
    void addToCart(Long cartId , Long productId , int quantity);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_cart SET quantity = ?1 where products_id = ?2 and cart_id = ?3" , nativeQuery = true)
    void updateQuantity(int quantity ,Long productId , Long cartId  );


    List<Object[]> getProductInCart(Long cartId);
//    void deleteProductInCart(Long productId , Long cartId);

}
