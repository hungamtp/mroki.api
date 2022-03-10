package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAdminDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.dto.productDTO.ProductUpdateDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.filter.ProductSpecification;
import mondays.net.mroki.api.filter.SearchCriteria;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        productRepository.findById(productId).orElseThrow(
                () -> new IllegalStateException(ErrorCode.PRODUCT_NOT_FOUND)
        );
        return true;
    }


    // admin service
    public void updateProduct(ProductUpdateDTO product) {

        Optional<Product> product1 = productRepository.findById(product.getId());
        product1.orElseThrow(
                () -> new IllegalStateException(ErrorCode.PRODUCT_NOT_FOUND)
        );
        Product updateProduct = product1.get();
         updateProduct.setCategory(new Category(product.getCategoryId()));
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setRetailPrice(product.getRetail());
        updateProduct.setModifiedDate(product.getModifiedDate());
        updateProduct.setProductImage(new ProductImage(product.getThumbnail(), product.getImage1(), product.getImage2()));
        productRepository.save(updateProduct);
    }

    public void save(Product product) {

        productRepository.save(product);

    }

    public void deleteProductById(Long id) {

        if (isExist(id))
            productRepository.deleteProductById(id);
        else throw new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND);

    }

    public void undeleteProductById(Long id) {

        if (isExist(id))
        {
            Product product = productRepository.findById(id).get();
            product.setDelete(false);
        }
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
        return productRepository.findByName(productName) == null ? false : true;
    }

}
