package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    List<Size> findByProduct(Product product);
}
