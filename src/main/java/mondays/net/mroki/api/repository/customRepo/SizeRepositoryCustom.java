package mondays.net.mroki.api.repository.customRepo;

import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface SizeRepositoryCustom {
    void reduceQuantity(List<ProductAddToCartDTO> carts);

}
