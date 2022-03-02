package mondays.net.mroki.api.dto.cartDetailDto;

import lombok.*;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveCartDTO {
    Long customerId;
    List<ProductAddToCartDTO> list;
}
