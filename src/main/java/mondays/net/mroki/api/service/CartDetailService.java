package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.cartDetailDto.AddCartDetailDTO;
import mondays.net.mroki.api.dto.cartDetailDto.CartDetailDTO;

import java.util.List;


public interface CartDetailService {

    void addToCart(AddCartDetailDTO addCartDetailDTO , String username);


    List<CartDetailDTO> getCartDetails(Long customerId);
}
