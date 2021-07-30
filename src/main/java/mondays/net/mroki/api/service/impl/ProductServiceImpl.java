package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAdminDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.filter.ProductSpecification;
import mondays.net.mroki.api.filter.SearchCriteria;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.ProductService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 9;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductConverter converter;


    public PageDTO findAllProduct(Pageable pageable, Specification specification) {

        try {
            PageDTO result = converter.entityToProductHomePageDTO(
                    productRepository.findAll(specification, pageable));
            return result;
        } catch (ProductConvertException ex) {
            throw new ProductConvertException(ErrorCode.PRODUCT_CONVERT_FAIL);
        }

    }


    public ProductDetailDTO getProductById(Long id) {

        Optional<Product> optional = productRepository.findById(id);

        if (optional.isPresent() && !optional.get().isDelete()) {

            try {
                return converter.entityToProductDetailDto(optional.get());
            } catch (ProductConvertException ex) {
                throw new ProductConvertException(ErrorCode.PRODUCT_CONVERT_FAIL);
            }

        } else {
            throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }

    }


    public boolean isExist(Long productId) {
        return productRepository.isExist(productId);
    }


    // admin service
    public void updateProduct(Product product) {

        if (!isExist(product.getId()))
            throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        else
            productRepository.save(product);

    }

    public void save(Product product) {
        List<Size> size = new ArrayList<>();

        for (int i = 35; i <= 47; i++) {
            size.add(Size.builder()
                    .size(i)
                    .quantity(100)
                    .build());
        }
        product.setSize(size);

        productRepository.save(product);

    }

    public void deleteProductById(Long id) {

        if (isExist(id))
            productRepository.deleteProductById(id);
        else throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);

    }

    public PageDTO findAllProductAdmin(Pageable pageable, Specification specification) {

        Page<Product> products = productRepository.findAll(specification, pageable);

        try {
            return converter.entityToPageAddDto(products);
        } catch (ProductConvertException ex) {
            throw new ProductConvertException(ErrorCode.PRODUCT_CONVERT_FAIL);
        }

    }

    @Override
    public Page<ProductAdminDTO> searchProduct(SearchCriteria searchCriteria) {

        List<Product> products = productRepository.findAll(new ProductSpecification(searchCriteria));
        Page<ProductAdminDTO> result = new PageImpl(products, PageRequest.of(0, 5), products.size());
        return result;
    }

    @Override
    public boolean isNameExist(String productName) {
        return productRepository.isNameExist(productName);
    }

}
