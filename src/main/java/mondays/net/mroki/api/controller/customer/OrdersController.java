package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/order")
@AllArgsConstructor
public class OrdersController {

    @Autowired
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDTO> order(@RequestBody List<ProductAddToCartDTO> cart, @RequestParam Long customerId) {

        ResponseDTO response = new ResponseDTO();
        try {
            if (orderService.order(cart, customerId) == null) {

                response.setErrorCode(ErrorCode.ORDER.toString());
                return ResponseEntity.badRequest().body(response);
            } else {
                response.setSuccessCode(SuccessCode.ORDER.toString());
                return ResponseEntity.ok().body(response);
            }

        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ORDER.toString());

            return ResponseEntity.badRequest().body(response);
        }

    }

}
