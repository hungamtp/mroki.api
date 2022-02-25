package mondays.net.mroki.api.repository.customRepo;

import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepositoryCustom {
    void reduceQuantity(List<ProductAddToCartDTO> carts);

}
