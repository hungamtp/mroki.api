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

                response.setSuccessCode(SuccessCode.ORDER_SUCCESS);
                return ResponseEntity.ok().body(response);
            } else {
                response.setErrorCode(ErrorCode.ORDER_FAIL);
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ORDER_FAIL);

            return ResponseEntity.badRequest().body(response);
        }

    }

}
