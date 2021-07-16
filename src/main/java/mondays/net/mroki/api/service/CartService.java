package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CartDTO;

public interface CartService {
    CartDTO getCart(Long customerId);

    void addProductToCart(Long cartId, Long productId , int quantity);

    boolean isExist(Long customerId);

    boolean isProductInCart(Long cartId , Long productId);

    void updateQuantity(int quantity, Long productId, Long cartId);
}
