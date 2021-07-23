package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.productDTO.ProductAdminDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    Page<ProductDTO> findAllProduct(Pageable pageable);

    ProductDetailDTO getProductById(Long id) throws Exception;


    Page<ProductDTO> getProductByCategory(String categoryId, Pageable pageable);

    Page<ProductDTO> getProductByName(String name, int page);

    boolean isExist(Long productId);

    //addmin service
    int countTotalElement();

    void deleteProductById(Long id);

    void save(Product product);

    void updateProduct(Product product);

    Page<ProductAdminDTO> findAllProductAdmin(Pageable pageable);


}
