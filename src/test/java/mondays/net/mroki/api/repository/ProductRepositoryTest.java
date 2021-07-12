package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

 // apply config in test package
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private  ProductRepository repo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
     public void checkFindProductByIsDelete(){

        repo.save(Product.builder().isDelete(false).build());
        repo.save(Product.builder().isDelete(false).build());
        repo.save(Product.builder().isDelete(false).build());
        Pageable pageable = PageRequest.of(0 , 3);

        List<Product> products = repo.findAllProduct(pageable);

        assertThat(products.size()).isEqualTo(3);
    }

    @Test
    public void getProductById(){
        repo.save(Product.builder().id(1L).name("hung").isDelete(false).build());
        repo.deleteProductById(1L);

        assertThat(repo.findProductById(1L).getName()).isEqualTo("hung");
    }

    @Test
     public void checkQuantity(){
        repo.save(Product.builder().id(1L).quantity(50).build());

        assertThat(repo.checkQuantity(40 , 1L)).isEqualTo(true);

    }

    @Test
     public void checkExistById(){
        repo.save(Product.builder().id(1L).build());

        assertThat(repo.findById(1L).isPresent()).isTrue();
    }

    @Test
     public void reduceQuantity(){
        repo.save(Product.builder().id(5L).quantity(50).build());

        repo.reduceQuantity(5L , 20);

        assertThat(repo.findById(1L).get().getQuantity()).isEqualTo(30);
    }






}