package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.dto.order.OrderInListDTO;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.CartDetail;
import mondays.net.mroki.api.entity.Product;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository repo;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;



    @Test
    public void testIsProductExist(){
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findOneByProductAndSizeAndCart(Product.builder().id(2L).build() , 41 , Cart.builder().id(1L).build());

        assertEquals(true , cartDetailOptional.isPresent());
    }
    @Test
    public void testIsNotProductExist(){
        Optional<CartDetail> cartDetailOptional = cartDetailRepository.findOneByProductAndSizeAndCart(Product.builder().id(10L).build() , 41 , Cart.builder().id(1L).build());

        assertEquals(false , cartDetailOptional.isPresent());
    }

    @Test
    public void orderTest(){
        List<OrderInListDTO> result = orderRepository.getOrderDetailByPhoneNumber("123");
        Assert.assertEquals( orderRepository.getOrderDetailByPhoneNumber("123").size(),3);


    }


}