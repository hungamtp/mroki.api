package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {


    @GetMapping("/{customerId}")
    public CartDTO getCart(@PathVariable Long customerId , HttpServletRequest request){

        CartDTO cart=(CartDTO) request.getSession().getAttribute(customerId.toString());

        if(cart== null){
            cart = new CartDTO();
            request.getSession().setAttribute(customerId.toString() , cart);
        }

        cart.setCustomer_id(customerId);

        return cart;

    }

    @PostMapping("/{customerId}")
    public void addProductToCart(@PathVariable Long customerId, @RequestBody ProductDTO product  , HttpServletRequest request){

        CartDTO cart=(CartDTO) request.getSession().getAttribute(customerId.toString());

        if(cart== null){
            cart = new CartDTO();
            cart.setCustomer_id(customerId);
        }

        // handle case: the product is already in cart  , just increase the quantity
//        if(cart.getCart().containsKey(product.getId()))
//            product.setQuantity(product.getQuantity() + 1);
//        else
//            product.setQuantity(1);

        //update cart
        cart.getCart().put(product.getId() , product);

        request.getSession().setAttribute(customerId.toString() , cart);
    }

}
