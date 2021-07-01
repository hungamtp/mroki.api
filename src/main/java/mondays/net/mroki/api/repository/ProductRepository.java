package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    // too much column needed
    @Query(value ="SELECT p from Product p WHERE is_delete = false")
    Page<Product> findProductByPage(@Param("page") Pageable page);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET is_delete = true WHERE id = ?1 LIMIT 1" , nativeQuery = true)
    void deleteProductById(Long id);

    @Query(value = "SELECT id FROM product WHERE id = ?1 LIMIT 1" , nativeQuery = true)
    Long getProductId(Long id);

    Page<Product> findProductByCategory(Category category , Pageable pageable );

}
