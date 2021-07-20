package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CartConverter;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.cart.CartDTO;
import mondays.net.mroki.api.dto.cart.CartIconDTO;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.repository.CartRepository;
import mondays.net.mroki.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository repo;

    @Autowired
    private final CartConverter converter;

    @Autowired
    private final ProductConverter productConverter;


    public void addToCart(Long customerId, Long productId, int quantity, int size) {

        if (isProductInCart(customerId, productId, size))
            updateQuantity(quantity, productId, customerId, size);
        else {
            Long cartId = repo.getCartId(customerId);
            repo.addToCart(cartId, productId, quantity, size);
        }

    }

    @Override
    public void updateQuantity(int quantity, Long productId, Long customerId, int size) {
        repo.updateQuantity(quantity, productId, customerId, size);
    }

    public CartDTO getCart(Long customerId) {

        if (!isExist(customerId)) {
            Cart newCart = Cart.builder()
                    .customer(Customer.builder().id(customerId).build())
                    .build();
            repo.save(newCart);
            return null;
        }

        return CartDTO.builder()
                .customerId(customerId)
                .products(productConverter.dataToCartDto(repo.getProductInCart(customerId)))
                .build();

    }


    public CartIconDTO getIconData(Long customerId) {
        if (!isExist(customerId)) {
            Cart newCart = Cart.builder()
                    .customer(Customer.builder().id(customerId).build())
                    .build();
            repo.save(newCart);
        }
        return converter.dataToDto(repo.getCountProductInCart(customerId).get(0));
    }

    public boolean isProductInCart(Long customerId, Long productId, int size) {
        return repo.isProductInCart(productId, customerId, size);
    }

    public boolean isExist(Long customerId) {
        return repo.isExist(customerId);
    }

}
