package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.product.ProductDTO;
import mondays.net.mroki.api.dto.product.ProductDetailDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.service.ProductService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 9;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductConverter converter;


    public Page<ProductDTO> findAllProduct(Pageable pageable) {
        try {
            Page<ProductDTO> result = converter.dataPageToPageDto(productRepository.findAllProduct(pageable));
            return result;
        } catch (ProductConvertException ex) {
            throw new ProductConvertException("CONVERT_FAIL");
        }


    }

    public ProductDetailDTO getProductById(Long id) {
        if (isExist(id)) {
            try {
                converter.entityToDetailDto(productRepository.findProductById(id));
            } catch (ProductConvertException ex) {
                throw new ProductConvertException("CONVERT_FAIL");
            }
        } else {
            throw new DataNotFoundException("PRODUCT_NOT_FOUND");
        }
        return null;

    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {

        if (isExist(id))
            productRepository.deleteProductById(id);
        else throw new IllegalIdentifierException("DELETE:product's id not found");

    }

    public Page<Product> getProductByCategory(String categoryId, Pageable pageable) {

        Category category = Category.builder()
                .id(categoryId)
                .build();

        return productRepository.findByCategory(category, pageable);

    }

    public Page<Product> getProductByName(String name, int page) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        return productRepository.findByNameLike(name, pageable);
    }

    public void updateProduct(Product product) {

        if (!isExist(product.getId()))
            throw new IllegalIdentifierException("Id not found");
        else
            productRepository.save(product);

    }

    public boolean isExist(Long productId) {
        return productRepository.isExist(productId);
    }

    public int countTotalElement() {
        return productRepository.getTotalElement();
    }

    public Page<ProductDTO> demo() {
        return converter.dataPageToPageDto(productRepository.findAllProductData(PageRequest.of(0, 5)));
    }

}
