package mondays.net.mroki.api.service;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

//    public Orders getOrder(Long userId){
//        return  orderRepository.findShoppingCart(userId);
//    }
}
