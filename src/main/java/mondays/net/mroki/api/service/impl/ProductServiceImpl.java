package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.dto.ProductDetailDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.service.ProductService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 9;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductConverter converter;


    public List<ProductDTO> getAllProductByPage(int page) {

        Pageable pageable = PageRequest.of(page , PAGE_SIZE);

        return converter.entityToDto(productRepository.findAllProduct(pageable));
    }


    public ProductDetailDTO getProductById(Long id) {

        if (!productRepository.checkExistById(id))
            throw new IllegalIdentifierException("GET_PRODUCT:product not found");

        return converter.entityToDetailDto(productRepository.findProductById(id));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {

        if (productRepository.checkExistById(id))
            productRepository.deleteProductById(id);
        else throw new IllegalIdentifierException("DELETE:product's id not found");

    }

    public List<ProductDTO> getProductByCategory(String categoryId, int page) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        List<ProductDTO> result = productRepository.findByCategory(categoryId , pageable).stream().map(product -> converter.entityToDto(product)).collect(Collectors.toList());
        return  result;

    }

    public Page<Product> getProductByName(String name, int page) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        return productRepository.findByNameLike(name, pageable);
    }

    public void updateProduct(Product product) {

        if (!productRepository.checkExistById(product.getId()))
            throw new IllegalIdentifierException("Id not found");
        else
            productRepository.save(product);

    }


}
