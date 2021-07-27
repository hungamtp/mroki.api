package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.cartDTO.CartDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> addProductToCart(@PathVariable Long customerId,
                                                        @Valid @RequestBody ProductAddToCartDTO product) {

        ResponseDTO response = new ResponseDTO();

        try {

            service.addToCart(customerId, product);

            response.setSuccessCode(SuccessCode.ADD_PRODUCT_TO_CART.toString());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ADD_PRODUCT_TO_CART.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> getCartIconDTO(@RequestParam Long customerId) {
        ResponseDTO response = new ResponseDTO();
        Integer count = service.getIconData(customerId);
        response.setData(count==null ? 0 : count);
        return ResponseEntity.ok().body(response);
    }


}
