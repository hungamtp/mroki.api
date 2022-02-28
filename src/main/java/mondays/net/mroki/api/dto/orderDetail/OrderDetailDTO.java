package mondays.net.mroki.api.dto.orderDetail;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {
    private Long productId;
    private String size;
    private String productImage;
    private int quantity;
    private float price;
}
