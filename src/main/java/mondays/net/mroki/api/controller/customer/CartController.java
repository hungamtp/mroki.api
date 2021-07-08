package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.ProductDTO;
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

        if(cart== null){
            cart = new CartDTO();
            cart.setCustomer_id(customerId);
            request.getSession().setAttribute(customerId.toString() , cart);
        }

        return cart.getProduct();

    }

    @PostMapping("/{customerId}")
    public void addProductToCart(@PathVariable Long customerId, @RequestBody ProductDTO product  , HttpServletRequest request){

        CartDTO cart=(CartDTO) request.getSession().getAttribute(customerId.toString());

        if(cart== null){
            cart = new CartDTO();
            cart.setCustomer_id(customerId);
        }

        // handle case: the product is already in cart  , just increase the quantity
        if(cart.getProduct().containsKey(product))
            cart.getProduct().put(product , cart.getProduct().get(product)+1);
        else
            cart.getProduct().put(product , 1);

        request.getSession().setAttribute(customerId.toString() , cart);

    }

}
