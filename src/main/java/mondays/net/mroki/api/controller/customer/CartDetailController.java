package mondays.net.mroki.api.controller.customer;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.cartDetailDto.AddCartDetailDTO;
import mondays.net.mroki.api.entity.CartDetail;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.security.jwt.JwtUtils;
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
    public ResponseEntity<ResponseDTO> retrieveAssignments(HttpServletRequest req ,
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

        return ResponseEntity.ok().body(responseDTO);
    }

}
