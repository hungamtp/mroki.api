package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.cart.CartDTO;
import mondays.net.mroki.api.dto.cart.CartIconDTO;


public interface CartService {
    CartDTO getCart(Long customerId);

    void addToCart(Long cartId, Long productId, int quantity, int size);


    void updateQuantity(int quantity, Long productId, Long customerId, int size);

    CartIconDTO getIconData(Long customerId);

}
