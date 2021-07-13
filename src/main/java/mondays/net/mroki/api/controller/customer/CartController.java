package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {

    @Autowired
    private final CartServiceImpl service;

    @GetMapping("/{customerId}")
    public CartDTO getCart(@PathVariable Long customerId ){

        return service.getCart(customerId);

    }

    @PostMapping("/{customerId}")
    public void addProductToCart(@PathVariable Long cartId, @RequestBody ProductDTO product  ){

        try{
            service.addProductToCart(cartId , product.getId() , 1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
