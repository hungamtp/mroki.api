package mondays.net.mroki.api.converter;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CartConverter {

    @Autowired
    private final ProductConverter converter;

    public CartDTO entityToDto(Cart cart){

        List<ProductDTO> products = converter.entityToDto(cart.getProducts());
        return CartDTO.builder()
                .id(cart.getId())
                .products(products)
                .build();
    }
}