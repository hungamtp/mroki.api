package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.CartIconDTO;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user/cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {

    @Autowired
    private final CartServiceImpl service;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long customerId) {

        return ResponseEntity.ok().body(service.getCart(customerId));

    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ResponseDTO> addProductToCart(@PathVariable Long cartId, @RequestBody ProductDTO product) {
        ResponseDTO response = new ResponseDTO();

        try {
            service.addProductToCart(cartId, product.getId(), 1);
            response.setSuccessCode(SuccessCode.ADD_PRODUCT_TO_CART);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ADD_PRODUCT_TO_CART);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping
    public ResponseEntity<CartIconDTO> getCartIconDTO(@RequestParam Long customerId) {
        return ResponseEntity.ok().body(service.getIconData(customerId));
    }


}
