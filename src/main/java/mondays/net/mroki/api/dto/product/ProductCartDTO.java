package mondays.net.mroki.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCartDTO{
    private Long id;
    private String name;
    private float rate;
    private String thumbnail;
    private float price;
    private int quantity;
    private int size;

}
