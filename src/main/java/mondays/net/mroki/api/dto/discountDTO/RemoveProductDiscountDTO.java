package mondays.net.mroki.api.dto.discountDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RemoveProductDiscountDTO {
    private Long productId;
    private Long discountId;
}
