package mondays.net.mroki.api.dto.cartDetailDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowCartDetailDTO {
    Long productId;
    String image;
    String size;
    int quantity;
    float price;
}
