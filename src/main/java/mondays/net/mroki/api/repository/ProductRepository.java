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

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.id , p.name,p.thumbnail ,p.price ,avg(c.rate) as rate , p.retail,p.sale_off, "+
            "p.category_id , p.created_date ,  p.modified_date ,p.description, p.is_delete ,p.image1 , p.image2 "+
            "FROM product p LEFT JOIN comment c ON p.id = c.product_id " +
            "WHERE p.is_delete = false " +
            "GROUP BY p.id ", nativeQuery = true)
    Page<Product> findAllProduct(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET is_delete = true WHERE id = ?1", nativeQuery = true)
    void deleteProductById(Long id);


    Page<Product> findByCategory(Category categoryId , Pageable pageable);

    Page<Product> findByNameLike(String name, Pageable pageable);

    @Query(value = "SELECT p.id , p.name,p.thumbnail ,p.price ,avg(c.rate) as rate , p.retail,p.sale_off, "+
            "p.category_id , p.created_date ,  p.modified_date ,p.description, p.is_delete ,p.image1 , p.image2 "+
            "FROM product p LEFT JOIN comment c ON p.id = c.product_id " +
            "WHERE p.is_delete = false  AND p.id = ?1 " +
            "GROUP BY p.id LIMIT 1", nativeQuery = true)
    Product findProductById(Long id);

    @Query(value = "SELECT CASE WHEN ?2 >  quantity  THEN true ELSE false END checkQuantity "+
            "FROM size s WHERE s.product_id = ?1 AND s.size =?3 " , nativeQuery = true)
    boolean checkQuantity(Long productId , int quantity , int size);

    @Query(value = "SELECT CASE " +
            "WHEN count(id) > 0 THEN true ELSE false " +
            "END checkExist " +
            "FROM product where id =?1 limit 1", nativeQuery = true)
    boolean isExist(Long productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE size SET quantity = "+
            "((SELECT quantity FROM size WHERE product_Id = ?1 AND size = ?3 LIMIT 1) - ?2 ) "+
            "WHERE product_Id = ?1 AND size =?3"
            , nativeQuery = true)
    void reduceQuantity(Long productId, int quantity , int size);

    @Query(value = "SELECT count(id) FROM product p  " , nativeQuery = true)
    int getTotalElement();
}
