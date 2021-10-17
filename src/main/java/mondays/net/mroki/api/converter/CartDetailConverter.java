package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.cartDetailDto.CartDetailDTO;
import mondays.net.mroki.api.entity.CartDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDetailConverter {

    public CartDetailDTO entityToDTO (CartDetail cartDetail){
        return CartDetailDTO.builder()
                .name(cartDetail.getProduct().getName())
                .productId(cartDetail.getProduct().getId())
                .price(cartDetail.getProduct().getPrice() * cartDetail.getQuantity())
                .quantity(cartDetail.getQuantity())
                .thumbnail(cartDetail.getProduct().getProductImage().getThumbnail())
                .size(cartDetail.getSize())
                .build();
    }

    public List<CartDetailDTO> entityToDtos (List<CartDetail> cartDetails){
        return cartDetails.stream()
                .map((cartDetail -> entityToDTO(cartDetail)))
                .collect(Collectors.toList());
    }
}
