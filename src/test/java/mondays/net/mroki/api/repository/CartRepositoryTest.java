package mondays.net.mroki.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository repo;

    @Test
    public void testIsExist(){
        boolean check = repo.isProductInCart(20L , 30L , 41);

        assertEquals(true , check);
    }

    @Test
    public void testDeleteProductInCart(){
        repo.deleteProductInCart(5L , 46 , 48L);
        boolean check = repo.isProductInCart(5L , 48L , 46);

        assertEquals(false , check);
    }

}