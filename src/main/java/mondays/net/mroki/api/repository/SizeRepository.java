package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.repository.customRepo.SizeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>  {

    List<Size> findByProduct(Product product);

    @Query(value = "SELECT CASE WHEN ?2 <  quantity  THEN true ELSE false END checkQuantity " +
            "FROM size s WHERE s.product_id = ?1 AND s.size =?3 ", nativeQuery = true)
    boolean isEnough(Long productId, int quantity, String size);

    Optional<Size> findBySizeAndProduct(String size,Product product);
}
