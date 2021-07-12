package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.id , p.name,p.thumbnail ,p.price ,avg(c.rate) as rate, p.quantity , p.retail,p.sale_off, "+
            "p.category_id , p.created_date ,  p.modified_date ,p.description, p.is_delete ,p.image1 , p.image2 "+
            "FROM product p LEFT JOIN comment c ON p.id = c.product_id " +
            "WHERE p.is_delete = false " +
            "GROUP BY p.id ", nativeQuery = true)
    List<Product> findAllProduct(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product p SET p.is_delete = false WHERE p.id = ?1 ", nativeQuery = true)
    void deleteProductById(Long id);

    @Query(value = "SELECT p.id , p.name,p.thumbnail ,p.price ,avg(c.rate) as rate, p.quantity , p.retail,p.sale_off, "+
            "p.category_id , p.created_date ,  p.modified_date ,p.description, p.is_delete ,p.image1 , p.image2 "+
            "FROM product p LEFT JOIN comment c ON p.id = c.product_id " +
            "WHERE p.is_delete = false AND category_id = ?1 " +
            "GROUP BY p.id ", nativeQuery = true)
    List<Product> findByCategory(String categoryId , Pageable pageable);

    Page<Product> findByNameLike(String name, Pageable pageable);

    @Query(value = "SELECT p.id , p.name,p.thumbnail ,p.price ,avg(c.rate) as rate, p.quantity , p.retail,p.sale_off, "+
            "p.category_id , p.created_date ,  p.modified_date ,p.description, p.is_delete ,p.image1 , p.image2 "+
            "FROM product p LEFT JOIN comment c ON p.id = c.product_id " +
            "WHERE p.is_delete = false  " +
            "GROUP BY p.id LIMIT 1", nativeQuery = true)
    Product findProductById(Long id);

    @Query(value = "SELECT CASE WHEN p.quantity > ?1 " +
            "THEN true ELSE false END " +
            "FROM product p " +
            "WHERE p.id = ?2", nativeQuery = true)
    boolean checkQuantity(int quantity, Long productId);

    @Query(value = "SELECT CASE " +
            "WHEN count(id) > 0 THEN true ELSE false " +
            "END checkExist " +
            "FROM product where id =?1 limit 1", nativeQuery = true)
    boolean checkExistById(Long productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET quantity = " +
            "((SELECT p.quantity FROM product p WHERE p.id = ?1 LIMIT 1) - ?2) " +
            "WHERE id =?1"
            , nativeQuery = true)
    void reduceQuantity(Long id, int quantity);


}
