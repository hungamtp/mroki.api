package mondays.net.mroki.api.dto.discountDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DiscountDTO {
    private Long id;
    private int discount;
}
