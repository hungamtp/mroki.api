package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.product.ProductAdminDTO;
import mondays.net.mroki.api.dto.product.ProductDTO;
import mondays.net.mroki.api.dto.product.ProductDetailDTO;
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

import java.util.Optional;

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

        Optional<Product> optional = productRepository.findById(id);

        if (optional.isPresent() && !optional.get().isDelete()) {

            try {
                return converter.entityToProductDetailDto(optional.get());
            } catch (ProductConvertException ex) {
                throw new ProductConvertException("CONVERT_FAIL");
            }

        } else {
            throw new DataNotFoundException("PRODUCT_NOT_FOUND");
        }

    }


    public Page<ProductDTO> getProductByCategory(String categoryId, Pageable pageable) {

        try {
            return converter.dataPageToPageDto(productRepository.findProductByCategory(categoryId, pageable));
        } catch (ProductConvertException ex) {
            throw new ProductConvertException("CONVERT_PRODUCT_FAIL");
        }

    }

    public Page<ProductDTO> getProductByName(String name, int page) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        return converter.dataPageToPageDto(productRepository.findByNameLike(name, pageable));
    }


    public boolean isExist(Long productId) {
        return productRepository.isExist(productId);
    }

    public int countTotalElement() {
        return productRepository.getTotalElement();
    }

    // admin service
    public void updateProduct(Product product) {

        if (!isExist(product.getId()))
            throw new IllegalIdentifierException("Id not found");
        else
            productRepository.save(product);

    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {

        if (isExist(id))
            productRepository.deleteProductById(id);
        else throw new DataNotFoundException("PRODUCT_NOT_FOUND");

    }

    public Page<ProductAdminDTO> findAllProductAdmin(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        try {
            return converter.entityToPageAddDto(products);
        } catch (ProductConvertException ex) {
            throw new ProductConvertException("PRODUCT_CONVERT_FAIL");
        }

    }

}
