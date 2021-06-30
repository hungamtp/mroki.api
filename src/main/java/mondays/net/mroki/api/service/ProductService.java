package mondays.net.mroki.api.service;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

//    private static final int PAGE_SIZE = 9;
    @Autowired
    private final ProductRepository productRepository;


    public Page<Product> findAllProduct(Pageable pageable){
        return  productRepository.findAllProduct(pageable);
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public void save(Product product){
        productRepository.save(product);
    }



}
