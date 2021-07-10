package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.service.ProductService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 9;

    @Autowired
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProductByPage(int page) {

        int offset = page * PAGE_SIZE;

        return convertEntityToProductDto(productRepository.findProductByPage(PAGE_SIZE, offset));
    }


    public ProductDTO getProductById(Long id) {

        if (!productRepository.checkExistById(id))
            throw new IllegalIdentifierException("GET_PRODUCT:product not found");

        return convertEntityToProductDto(productRepository.findProductById(id)).get(0);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {

        if (productRepository.checkExistById(id)) productRepository.deleteProductById(id);
        else throw new IllegalIdentifierException("DELETE:product's id not found");

    }

    public Page<Product> getProductByCategory(String categoryId, int page) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Category category = new Category(categoryId);

        return productRepository.findProductByCategory(category, pageable);

    }

    public Page<Product> getProductByName(String name, int page) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        return productRepository.findByNameLike(name, pageable);
    }

    public void updateProduct(Product product) {

        if (productRepository.checkExistById(product.getId()))
            throw new IllegalIdentifierException("Id not found");

        productRepository.save(product);

    }

    List<ProductDTO> convertEntityToProductDto(List<Object[]> products) {

        List<ProductDTO> result = new ArrayList<>();

        products.stream().forEach((product) -> {

            Long id = ((BigInteger) product[0]).longValue();
            String name = (String) product[1];
            String thumbnail = (String) product[2];
            float price = (float) product[3];
            // handler case no rate in product
            float rate = 0;
            if (Optional.ofNullable(product[4]).isPresent()) {
                rate = ((BigInteger) product[0]).floatValue();
            }
            String category_id = (String) product[5];

            result.add(new ProductDTO(id, name, rate, thumbnail, price));

        });

        return result;

    }

//    ProductDTO convertDataToProductDTO(Object[] data) {
//
//            Long id = ((BigInteger) data[0]).longValue();
//            String name = (String) data[1];
//            String thumbnail = (String) data[2];
//            float price = (float) data[3];
//            float rate = 0;
//            if (Optional.ofNullable(data[4]).isPresent()) {  // handle case : product has no rate
//                rate = ((BigInteger) data[0]).floatValue();
//            }
//            String category_id = (String) data[5];
//
//        return new ProductDTO(id, name, rate, thumbnail, price);
//
//    }


}
