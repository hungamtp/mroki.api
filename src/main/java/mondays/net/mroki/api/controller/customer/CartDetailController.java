package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.cartDetailDto.AddCartDetailDTO;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.CartDetailService;
import mondays.net.mroki.api.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
@RequestMapping("/cartdetail")
@AllArgsConstructor
public class CartDetailController {

    private CartDetailService cartDetailService;
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addToCart(HttpServletRequest req ,
                                                           @RequestBody AddCartDetailDTO addCartDetail)
                                                                                         throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();

        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(jwt.split("\\.")[1]));
        String sub = payload.split(",")[0];
        String username = sub.substring(8 ,sub.length()-1);

        cartDetailService.addToCart(addCartDetail , username);

        responseDTO.setData(addCartDetail);
        responseDTO.setSuccessCode(SuccessCode.ADD_PRODUCT_TO_CART_SUCCESS);

        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllCartDetail(HttpServletRequest req )
            throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();

        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(jwt.split("\\.")[1]));
        String sub = payload.split(",")[0];
        String username = sub.substring(8 ,sub.length()-1);

       Long customerId = customerService.findByUsername(username).getId();

        responseDTO.setData(cartDetailService.getCartDetails(customerId));
        responseDTO.setSuccessCode(SuccessCode.GET_ALL_CART_DETAIL);

        return ResponseEntity.ok().body(responseDTO);
    }

}
