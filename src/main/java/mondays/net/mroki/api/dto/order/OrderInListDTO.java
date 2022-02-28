package mondays.net.mroki.api.dto.order;



import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInListDTO {
    private Long id;
    private String customerName;
    private String address;
    private String phoneNumber;
    private LocalDate createdDate;
    private double totalBill;

}
