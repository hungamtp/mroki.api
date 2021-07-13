package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CartConverter;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.CartIconDTO;
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


    public void addProductToCart(Long cartId, Long productId, int quantity) {

        if (repo.isProductInCart(cartId, productId))
            repo.updateQuantity(quantity, productId, cartId);
        else
            repo.addToCart(cartId, productId, quantity);

    }

    public CartDTO getCart(Long customerId) {

        if (!repo.isExist(customerId)) {
            Cart newCart = Cart.builder()
                    .customer(Customer.builder().id(customerId).build())
                    .build();
            repo.save(newCart);
            return null;
        }

        return CartDTO.builder()
                .customerId(customerId)
                .products(productConverter.dataToDto(repo.getProductInCart(customerId)))
                .build();

    }

    public CartIconDTO getIconData(Long customerId){
        return converter.dataToDto(repo.getCountProductInCart(customerId).get(0));
    }

}
