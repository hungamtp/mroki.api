package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProductByPage(int page);

    ProductDTO getProductById(Long id) throws Exception;

    void save(Product product);

    void updateProduct(Product product);

    List<ProductDTO> getProductByCategory(String categoryId, int page);

    Page<Product> getProductByName(String name, int page);
}
