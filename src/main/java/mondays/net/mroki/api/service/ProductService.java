package mondays.net.mroki.api.service;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.ProductRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private static final int PAGE_SIZE =9;

    @Autowired
    private final ProductRepository productRepository;


    public Page<Product> findAllProduct(int page){

        Pageable pageable = PageRequest.of(page ,PAGE_SIZE);

        return  productRepository.findProductByPage(pageable);
    }

    public Product getProductById(Long id) throws Exception {

        Optional<Product> productOptional = productRepository.findById(id);

        if(!productOptional.isPresent()){
            throw  new IllegalIdentifierException("GET_PRODUCT:product not found");
        }

        return productOptional.get();
    }

    public void save(Product product){

        productRepository.save(product);

    }

    public void deleteProductById(Long id){

        Optional<Long> productOptional = Optional.ofNullable(productRepository.getProductId(id));

        if(productOptional.isPresent())  productRepository.deleteProductById(id);

        else throw  new IllegalIdentifierException("DELETE:product's id not found");

    }





}
