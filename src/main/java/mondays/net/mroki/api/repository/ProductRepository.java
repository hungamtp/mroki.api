package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

   // @Query(value = "SELECT id , name , thumbnail  ,price ,category_id , created_date , is_delete FROM product WHERE  is_delete = false " , nativeQuery = true)
    @Query(value = "SELECT * FROM product WHERE  is_delete = false " , nativeQuery = true)
    Page<Product> findAllProduct(@Param("page") Pageable page);



}
