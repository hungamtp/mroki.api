package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDetailDTO {

    private Long id;
    private String name;
    private float retail;
    private String description;
    private int saleOff;
    private String categoryId;
    private String thumbnail;
    private String image1;
    private String image2;

}
