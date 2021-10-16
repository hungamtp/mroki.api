package mondays.net.mroki.api.dto.cartDetailDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddCartDetailDTO {
    private int quantity;
    private int size;
    private Long productId;
}
