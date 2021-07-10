package mondays.net.mroki.api.repository;


import mondays.net.mroki.api.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest // apply config in test package
class ProductRepositoryTest {

    @Autowired
    private  ProductRepository repo;


    @Test
    public void findProductTest(){

       List<Object[]> product = repo.findProductById(5L);

       if(!repo.checkExistById(5L))
           assertThat(product.size()).isEqualTo(0);
       else
           assertThat(product.get(0)[0]).isEqualTo(5L);

    }


    @Test
    public void checkFindProductByNameTest(){

        Product product = Product.builder()
                .name("product")
                .quantity(50)
                .build();

        repo.save(product);

        Page<Product> productPage = repo.findByNameLike("product" , PageRequest.of(0 ,1));

        assertThat(productPage).isNotNull();
    }





}