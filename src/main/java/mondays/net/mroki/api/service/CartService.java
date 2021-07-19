package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.cart.CartDTO;
import mondays.net.mroki.api.dto.cart.CartIconDTO;
import org.springframework.stereotype.Service;

public interface CartService {
    CartDTO getCart(Long customerId);

    void addProductToCart(Long cartId, Long productId, int quantity, int size);

    boolean isExist(Long customerId);

    boolean isProductInCart(Long cartId, Long productId, int size);

    void updateQuantity(int quantity, Long productId, Long cartId, int size);

    CartIconDTO getIconData(Long customerId);

}
