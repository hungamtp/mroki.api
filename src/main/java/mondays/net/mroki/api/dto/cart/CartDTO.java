package mondays.net.mroki.api.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.dto.product.ProductCartDTO;

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
