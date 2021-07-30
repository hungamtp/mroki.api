package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>  {

    @Query(value = "SELECT p.id , p.name,p.thumbnail ,p.price ,avg(c.rate) as rate , p.retail,p.sale_off " +
            "FROM product p LEFT JOIN comment c ON p.id = c.product_id " +
            "WHERE p.is_delete = false " +
            "GROUP BY p.id ", nativeQuery = true)
    Page<Object[]> findAllProduct(Pageable pageable , Specification specification);


    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET is_delete = true WHERE id = ?1", nativeQuery = true)
    void deleteProductById(Long id);



    @Query(value = "SELECT CASE " +
            "WHEN count(id) > 0 THEN true ELSE false " +
            "END checkExist " +
            "FROM product where id =?1 limit 1", nativeQuery = true)
    boolean isExist(Long productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE size SET quantity = " +
            "((SELECT quantity FROM size WHERE product_Id = ?1 AND size = ?3 LIMIT 1) - ?2 ) " +
            "WHERE product_Id = ?1 AND size =?3"
            , nativeQuery = true)
    void reduceQuantity(Long productId, int quantity, int size);

    @Query(value = "SELECT CASE WHEN count(p.id) >0  THEN true ELSE false END checkQuantity "+
            "FROM product p WHERE  p.name = ?1 LIMIT 1")
    boolean isNameExist(String name);



}
