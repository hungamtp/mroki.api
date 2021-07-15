package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private  ProductRepository repo;

    @Test
    public void findAllProduct(){


        assertEquals(repo.findProductById(31L).getName() , "p1");
    }

    @Test
     public void checkFindProductByIsDelete(){

        Pageable pageable = PageRequest.of(0 , 3);

        repo.save(Product.builder().name("hung").build());
        when(repo.findProductById(1L)).thenReturn(Product.builder().name("test").build());

        assertEquals(repo.findProductById(1L).getName() , "test");
    }

    @Test
    public void getProductById(){
        repo.save(Product.builder().id(1L).name("hung").isDelete(false).build());
        repo.deleteProductById(1L);

        assertThat(repo.findProductById(1L)).isNull();
    }

    @Test
     public void checkQuantity(){

        repo.save(Product.builder().id(1L).quantity(50).build());

        assertThat(repo.checkQuantity(40 , 1L)).isEqualTo(false);

    }

    @Test
     public void checkExistById(){
        repo.save(Product.builder().id(1L).name("demo").build());

        assertThat(repo.findById(1L).isPresent()).isTrue();
    }

    @Test
     public void reduceQuantity(){
        repo.save(Product.builder().id(5L).quantity(50).build());

        repo.reduceQuantity(5L , 20);

        assertThat(repo.findById(1L).get().getQuantity()).isEqualTo(30);
    }






}