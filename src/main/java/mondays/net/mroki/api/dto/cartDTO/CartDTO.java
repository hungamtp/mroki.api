package mondays.net.mroki.api.dto.cartDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.dto.productDTO.ProductCartDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    private Long id;
    private Long customerId;

    private List<ProductCartDTO> products = new ArrayList<>();
}
