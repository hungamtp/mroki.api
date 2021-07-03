package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
public class CartController {

    @Autowired
    private final CartService cartService;

    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable Long customerId){
        return cartService.getCart(customerId);
    }

    @PostMapping
    public void addProductToCart(@RequestParam Long cartId ,@RequestParam Long productId){
        cartService.addProductToCart(cartId , productId);
    }
    
}
