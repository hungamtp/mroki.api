package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.dto.CartDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
public class CartController {




    @GetMapping("/{customerId}")
    public HashMap<ProductDTO , Integer> getCart(@PathVariable Long customerId , HttpServletRequest request){

        CartDTO cart=(CartDTO) request.getSession().getAttribute(customerId.toString());

        if(cart== null)
            cart = new CartDTO();

        return cart.getProduct();

    }

    @PostMapping("/{customerId}")
    public void addProductToCart(@PathVariable Long customerId, @RequestBody ProductDTO product  , HttpServletRequest request){

        CartDTO cart=(CartDTO) request.getSession().getAttribute(customerId.toString());

        if(cart == null) cart = new CartDTO();


            cart.getProduct().put(product , 1);

    }

}
