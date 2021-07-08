package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CartDTO;

public interface CartService {
    public CartDTO getCart(Long customerId);

    public String addProductToCart(Long cartId, Long productId);
}
