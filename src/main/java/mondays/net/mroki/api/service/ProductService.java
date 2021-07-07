package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    public Page<ProductDTO> getAllProduct(int page);
    public Product getProductById(Long id) throws Exception;
    public void save(Product product);
    public Page<Product> getProductByCategory(String categoryId , int page);
    public Page<Product> getProductByName(String name , int page);
}
