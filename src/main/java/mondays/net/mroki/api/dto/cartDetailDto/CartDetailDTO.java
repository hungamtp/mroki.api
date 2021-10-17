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
    private int quantity;
    private int size;
    private float price;
}
