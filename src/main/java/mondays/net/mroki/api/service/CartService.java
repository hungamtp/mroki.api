package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CartDTO;

public interface CartService {
    CartDTO getCart(Long customerId);

    void addProductToCart(Long cartId, Long productId , int quantity);
}
