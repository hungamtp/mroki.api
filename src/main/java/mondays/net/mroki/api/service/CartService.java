package mondays.net.mroki.api.service;

import mondays.net.mroki.api.entity.Cart;

public interface CartService  {
    public Cart getCart(Long customerId);
    public String addProductToCart(Long cartId , Long productId);
}
