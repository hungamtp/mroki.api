package mondays.net.mroki.api.converter;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.cartDTO.CartIconDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

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
