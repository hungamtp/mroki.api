package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SizeRepositoryTest {

    @Autowired
    private SizeRepository repo;

    @Test
    public void findByProduct(){
        List<Size> sizes = repo.findByProduct(Product.builder().id(5L).build());

        assertEquals(100 , sizes.get(0).getQuantity());
    }

}