package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkExist " +
            "FROM cart where customer_id =?1 limit 1", nativeQuery = true)
    boolean isExist(Long customerId);

    @Query(value = "SELECT CASE WHEN count(products_id) > 0 THEN true ELSE false END checkExist " +
            "FROM product_cart where products_id = ?1 AND cart_id = ?2 AND size = ?3 LIMIT 1", nativeQuery = true)
    boolean isProductInCart(Long productId, Long cartId , int size);

    @Query(value = "INSERT INTO product_cart(cart_id , products_id , quantity , size) "+
            "VALUES(?1 , ?2 , ?3 , ?4)", nativeQuery = true)
    void addToCart(Long cartId, Long productId, int quantity , int size);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_cart c SET quantity = "+
            //get old quantity then add with new quantity
            "(?1+(SELECT quantity FROM product_cart pc WHERE pc.products_id =?2 AND pc.cart_id =?3 LIMIT 1 )) "+
            "WHERE c.cart_id  = ?3 AND c.products_id =?2 AND size = ?4", nativeQuery = true)
    void updateQuantity(int quantity, Long productId, Long cartId , int size);


    @Query(value = "SELECT p.id , p.name , p.thumbnail , p.price , pc.quantity as quantity ,pc.size ,  " +
            "(SELECT avg(c.rate) as rate FROM product p2 LEFT JOIN comment  c ON c.product_id = p2.id) " +
            "FROM product_cart pc " +
            "LEFT JOIN product p ON pc.products_id = p.id " +
            "WHERE pc.cart_id = (SELECT id FROM cart c WHERE c.customer_id = ?1 LIMIT 1)", nativeQuery = true)
    List<Object[]> getProductInCart(Long customerId);

    @Query(value = "DELETE FROM product_cart WHERE products_id = ?1 AND cart_id = ?2 ", nativeQuery = true)
    void deleteProductInCart(Long productId, Long cartId);


    @Query(value = "SELECT pc.cart_id , count(products_id) as count FROM product_cart pc " +
            "GROUP BY pc.cart_id " +
            "HAVING pc.cart_id =(SELECT id FROM cart c WHERE c.customer_id = ?1 LIMIT 1)" , nativeQuery = true)
    List<Object[]> getCountProductInCart(Long customerId);




}
