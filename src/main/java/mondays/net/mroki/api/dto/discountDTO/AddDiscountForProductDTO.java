package mondays.net.mroki.api.dto.discountDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDiscountForProductDTO {
    private List<Long> productId;
    private Long discountId;
}
