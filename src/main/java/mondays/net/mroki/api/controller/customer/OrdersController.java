package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.product.ProductDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> order(@RequestBody List<ProductDTO> cart, @RequestParam Long customerId) {

        ResponseDTO response = new ResponseDTO();
        try {
            orderService.order(cart, customerId);
            response.setSuccessCode(SuccessCode.ORDER);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ORDER);

            return ResponseEntity.badRequest().body(response);
        }

    }

}
