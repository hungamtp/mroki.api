package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.filter.ProductSpecification;
import mondays.net.mroki.api.filter.SearchCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repo;


    @Test
    public void deleteProductById() {

        assertEquals(repo.isExist(5L), true);

    }


    @Test
    public void findProductByID() {
        assertEquals(repo.findById(5L).get().getName(), "Nike 2");
        assertNull(repo.findById(-1L));
        assertNotEquals(repo.findById(5L).get().getName(), "Nike 3");
    }


    @Test
    public void isExist(){
        assertEquals(repo.isExist(5L) , true);
    }



    @Test
    public void search(){
        Specification<Product> specification= null;


          specification = new ProductSpecification(new SearchCriteria("price" , ":" , 150));


          Page<Product>   product = repo.findAll(specification, PageRequest.of(0,5));


        product.forEach((pro) ->assertEquals(pro.getPrice() ,150 ));

    }

    @Test
    public void isEnoughTest() throws Exception {
        repo.isEnough(2L , "M" , 13);
    }





}