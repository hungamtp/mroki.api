package mondays.net.mroki.api.controller.customer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Documented;

@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
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
        if(cart.getProduct().containsKey(product))
            cart.getProduct().put(product , cart.getProduct().get(product)+1);
        else
            cart.getProduct().put(product , 1);

        request.getSession().setAttribute(customerId.toString() , cart);

    }

}
