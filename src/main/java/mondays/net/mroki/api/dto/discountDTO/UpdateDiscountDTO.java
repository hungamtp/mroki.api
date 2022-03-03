package mondays.net.mroki.api.dto.discountDTO;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateDiscountDTO {
    private Long id;
    private int saleOff;
    private LocalDate startDate;
    private LocalDate endDate;
}
