package mondays.net.mroki.api.repository;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@DataJpaTest
class ProductRepositoryTest {


    @Autowired
    private  ProductRepository repo;

    @Test
    public void test(){
        Product product = Product.builder()
                                .id(50L)
                                .name("test")
                                .build();

        repo.save(product);

        assertThat(product.getName()).isEqualTo("test");

    }

}