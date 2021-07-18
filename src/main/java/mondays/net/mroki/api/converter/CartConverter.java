package mondays.net.mroki.api.converter;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.cart.CartDTO;
import mondays.net.mroki.api.dto.cart.CartIconDTO;
import mondays.net.mroki.api.dto.product.ProductDTO;
import mondays.net.mroki.api.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@AllArgsConstructor
public class CartConverter {

    @Autowired
    private final ProductConverter converter;

//    public CartDTO entityToDto(Cart cart){
//
//        List<ProductDTO> products = converter.entityToDto(cart.getProducts());
//        return CartDTO.builder()
//                .id(cart.getId())
//                .products(products)
//                .build();
//    }

    public CartIconDTO dataToDto(Object[] data){
        return new CartIconDTO(((BigInteger)data[0]).longValue() ,((BigInteger) data[1]).intValue());
    }

}
