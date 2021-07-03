package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/order")
@AllArgsConstructor
public class OrdersController {

    @Autowired
    private final OrderService orderService;

    @PostMapping
    public void order(@RequestBody Cart cart){
        orderService.order(cart);
    }

    @GetMapping("/{customerId}")
    public List<Orders> ordersList(@PathVariable Long customerId){
        return orderService.orders(customerId);
    }


}
