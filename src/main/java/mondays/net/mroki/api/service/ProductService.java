package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAdminDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.filter.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface ProductService {
    PageDTO findAllProduct(Pageable pageable, Specification specification);

    ProductDetailDTO getProductById(Long id) throws Exception;


    boolean isExist(Long productId);

    //addmin service

    void deleteProductById(Long id);

    void save(Product product);

    void updateProduct(Product product);

    PageDTO findAllProductAdmin(Pageable pageable, Specification spec);

    Page<ProductAdminDTO> searchProduct(SearchCriteria searchCriteria);

    boolean isNameExist(String productName);


}
