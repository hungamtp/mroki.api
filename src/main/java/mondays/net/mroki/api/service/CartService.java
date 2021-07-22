package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.cart.CartDTO;
import mondays.net.mroki.api.dto.cart.CartIconDTO;
import mondays.net.mroki.api.dto.product.ProductAddToCartDTO;


public interface CartService {
    CartDTO getCart(Long customerId);

    void addToCart(Long cartId, ProductAddToCartDTO product);


    void updateQuantity(int quantity, Long productId, Long customerId, int size);

    Integer getIconData(Long customerId);

}
