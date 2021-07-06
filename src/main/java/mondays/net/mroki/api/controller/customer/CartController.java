package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
public class CartController {


    @Autowired
    private final CartServiceImpl cartService;

    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable Long customerId){
        return cartService.getCart(customerId);
    }

    @PostMapping
    public String addProductToCart(@RequestParam Long cartId ,@RequestParam Long productId){
        return cartService.addProductToCart(cartId , productId);
    }
    
}