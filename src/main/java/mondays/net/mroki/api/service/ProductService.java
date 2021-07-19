package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.product.ProductDetailDTO;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ProductService {
    Page<Product> findAllProduct(Pageable pageable);

    ProductDetailDTO getProductById(Long id) throws Exception;

    void save(Product product);

    void updateProduct(Product product);

    Page<Product> getProductByCategory(String categoryId, Pageable pageable);

    Page<Product> getProductByName(String name, int page);

    boolean isExist(Long productId);

    int countTotalElement();

    void deleteProductById(Long id);
}
