package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/order")
@AllArgsConstructor
public class OrdersController {

    @Autowired
    private final OrderServiceImpl orderService;

    @PostMapping
    public void order(@RequestBody List<ProductDTO> cart , @RequestParam Long customerId) {

        orderService.order(cart , customerId);
    }



}
