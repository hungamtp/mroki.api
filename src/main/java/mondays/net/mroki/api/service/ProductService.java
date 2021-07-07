package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProductByPage(int page);
    public Product getProductById(Long id) throws Exception;
    public void save(Product product);
    public Page<Product> getProductByCategory(String categoryId , int page);
    public Page<Product> getProductByName(String name , int page);
}
