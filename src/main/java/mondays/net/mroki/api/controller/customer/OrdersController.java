package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/order")
@AllArgsConstructor
public class OrdersController {

    @Autowired
    private final OrderService orderService;

//    @GetMapping
//    public Orders showShopingCart(Long userId){
//        return orderService.getOrder(userId);
//    }

}
