package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CartConverter;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.cartDTO.CartDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
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


    public void addToCart(Long customerId, ProductAddToCartDTO product) {

        if (repo.isProductInCart(product.getId(), customerId, product.getSize()))
            repo.updateQuantity(product.getQuantity(), product.getId(), customerId, product.getSize());
        else {
            Long cartId = repo.getCartId(customerId);
            repo.addToCart(cartId, product.getId(), product.getQuantity(), product.getSize());
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


    public Integer getIconData(Long customerId) {
        if (!isExist(customerId)) {
            Cart newCart = Cart.builder()
                    .customer(Customer.builder().id(customerId).build())
                    .build();
            repo.save(newCart);
        }
        return repo.getCountProductInCart(customerId);
    }

    public boolean isProductInCart(Long cartId, Long productId, int size) {
        return repo.isProductInCart(productId, cartId, size);
    }

    public boolean isExist(Long customerId) {
        return repo.isExist(customerId);
    }

}
