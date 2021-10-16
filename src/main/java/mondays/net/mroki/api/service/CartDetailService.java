package mondays.net.mroki.api.service;


import mondays.net.mroki.api.dto.cartDetailDto.AddCartDetailDTO;


public interface CartDetailService {

    void addToCart(AddCartDetailDTO addCartDetailDTO , String username);
}
