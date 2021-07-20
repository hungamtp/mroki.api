package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.cart.CartDTO;
import mondays.net.mroki.api.dto.cart.CartIconDTO;
import mondays.net.mroki.api.dto.product.ProductAddToCartDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {

    @Autowired
    private final CartService service;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long customerId) {

        return ResponseEntity.ok().body(service.getCart(customerId));

    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ResponseDTO> addProductToCart(@PathVariable Long customerId,
                                                        @Valid @RequestBody ProductAddToCartDTO product) {

        ResponseDTO response = new ResponseDTO();

        try {

            service.addToCart(customerId, product.getId(), product.getQuantity() , product.getSize());

            response.setSuccessCode(SuccessCode.ADD_PRODUCT_TO_CART.toString());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ADD_PRODUCT_TO_CART.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping
    public ResponseEntity<CartIconDTO> getCartIconDTO(@RequestParam Long customerId) {
        return ResponseEntity.ok().body(service.getIconData(customerId));
    }


}
