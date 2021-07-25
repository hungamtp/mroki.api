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
    public void findAllProduct() {
//        Page<Product> list = repo.findAllProduct(PageRequest.of(0, 9 , Sort.by("id") ) );
//
//        assertEquals(list.get().collect(Collectors.toList()).get(0).getId(), 2L);
    }

    @Test
    public void deleteProductById() {

        assertEquals(repo.isExist(5L), true);

    }


    @Test
    public void findProductByID() {
//        assertEquals(repo.findProductById(5L).getName(), "Nike 2");
//        assertNull(repo.findProductById(-1L));
//        assertNotEquals(repo.findProductById(5L).getName(), "Nike 3");
    }

    @Test
    public void checkQuantity() {
        assertEquals(repo.checkQuantity(5L , 5 , 41) , true);
    }

    @Test
    public void isExist(){
        assertEquals(repo.isExist(5L) , true);
    }


    @Test
    public void findAllProductAdmin(){
//        Page<Product> products=repo.findByIsDeleteIsFalse(PageRequest.of(0 , 1 , Sort.by("id")));
//
//        assertEquals(2L ,products.getContent().get(0).getId() );
    }

    @Test
    public void search(){
        Specification<Product> specification= null;


          specification = new ProductSpecification(new SearchCriteria("price" , ":" , 150));


          Page<Product>   product = repo.findAll(specification, PageRequest.of(0,5));


        product.forEach((pro) ->assertEquals(pro.getPrice() ,150 ));

    }




}