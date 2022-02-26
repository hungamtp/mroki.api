package mondays.net.mroki.api.dto.discountDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddDiscountDTO {
    private int saleOff;
    private LocalDate startDate;
    private LocalDate endDate;
}
