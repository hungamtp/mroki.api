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


    @Query(value = "SELECT p.id , p.name  , p.thumbnail ,p.price ,avg(c.rate) as rate, p.category_id FROM product" +
            " p LEFT JOIN comment c on p.id = c.product_id " +
            "GROUP BY p.id limit ?1 offset ?2", nativeQuery = true)
    List<Object[]> findProductByPage(int limit, int offset);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET is_delete = true WHERE id = ?1 LIMIT 1", nativeQuery = true)
    void deleteProductById(Long id);

    @Query(value = "SELECT id FROM product WHERE id = ?1 LIMIT 1", nativeQuery = true)
    Long getProductId(Long id);

    Page<Product> findProductByCategory(Category category, Pageable pageable);

    Page<Product> findByNameLike(String name, Pageable pageable);

    @Query(value = "SELECT p.id , p.name  , p.thumbnail ,p.price ,avg(c.rate) as rate, p.category_id FROM product " +
            "p LEFT JOIN comment c on p.id = c.product_id " +
            "WHERE p.id = ?1 " +
            "GROUP BY p.id limit 1", nativeQuery = true)
    List<Object[]> findProductById(Long id);

    @Query(value = "SELECT CASE" +
            "WHEN count(id) > 0 THEN true ELSE false" +
            "END checkExist" +
            "FROM product where id =?1 limit 1" , nativeQuery = true)
    boolean checkExistById(Long productId);

}
