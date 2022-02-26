package mondays.net.mroki.api.dto.discountDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDiscountForProductDTO {
    private Long productId;
    private Long discountId;
}
