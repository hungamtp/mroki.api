package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.customerDTO.CustomerOrderDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.OrderService;
import mondays.net.mroki.api.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/order")
@AllArgsConstructor
public class OrdersController {

    private final OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<ResponseDTO> order(@RequestParam(required = false) Long customerId , @RequestBody CustomerOrderDTO customerOrderDTO) {

        ResponseDTO response = new ResponseDTO();
        try {
            List<String> productIsNotEnough = orderService.order(customerOrderDTO.getCart(), customerId , customerOrderDTO);
            if (productIsNotEnough == null) {

                response.setSuccessCode(SuccessCode.ORDER_SUCCESS);
                return ResponseEntity.ok().body(response);
            } else {
                response.setErrorCode(ErrorCode.ORDER_FAIL);
                response.setData(productIsNotEnough);
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ORDER_FAIL);

            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllOrderByPhoneNumberOrEmail(@RequestParam String phoneNumberOrEmail , @RequestParam boolean isPhone) {

        ResponseDTO response = new ResponseDTO();
        try {

            response.setData(orderService.findAllOrderByPhoneNumber(phoneNumberOrEmail , isPhone));
            response.setSuccessCode(SuccessCode.GET_ORDER_SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.setErrorCode(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ResponseDTO> getAllOrderByPhoneNumber(@PathVariable Long customerId) {

        ResponseDTO response = new ResponseDTO();
        try {

            response.setData(orderService.findAllOrderByCustomerId(customerId));
            response.setSuccessCode(SuccessCode.GET_ORDER_SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.setErrorCode(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/orderDetail/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetail(@PathVariable Long orderId) {

        ResponseDTO response = new ResponseDTO();
        try {

            response.setData(orderService.getOrderDetails(orderId));
            response.setSuccessCode(SuccessCode.GET_ORDER_SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.setErrorCode(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

}
