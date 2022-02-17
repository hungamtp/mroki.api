package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductHomeDTO {
    private Long id;
    private String name;
    private float rate;
    private String thumbnail;
    private float price;
    private String discount;
    private boolean isSoldOut;
}
