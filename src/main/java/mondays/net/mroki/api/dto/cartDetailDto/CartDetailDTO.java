package mondays.net.mroki.api.dto.cartDetailDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailDTO {
    private String thumbnail;
    private String name;
    private Long productId;
    private int quantity;
    private String size;
    private float price;
}
