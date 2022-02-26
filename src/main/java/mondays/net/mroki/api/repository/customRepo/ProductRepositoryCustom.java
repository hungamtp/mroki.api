package mondays.net.mroki.api.repository.customRepo;

import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepositoryCustom {
    Boolean isEnough(Long productId , String size , int quantity) throws Exception;
    void addDiscount(List<Long> productId , Long discountId);
}
