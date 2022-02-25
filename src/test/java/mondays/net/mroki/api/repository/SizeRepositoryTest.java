package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SizeRepositoryTest {

    @Autowired
    private SizeRepository repo;

    @Test
    public void findByProduct(){
        repo.isEnough(2L, 2 , "M");
        List<ProductAddToCartDTO> cart = new ArrayList<>();
        cart.add(new ProductAddToCartDTO(2L , 4 , "M"));
//        repo.reduceQuantity(cart);
    }

}