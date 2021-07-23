package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.cartDTO.CartDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;


public interface CartService {
    CartDTO getCart(Long customerId);

    void addToCart(Long cartId, ProductAddToCartDTO product);


    void updateQuantity(int quantity, Long productId, Long customerId, int size);

    Integer getIconData(Long customerId);

}
