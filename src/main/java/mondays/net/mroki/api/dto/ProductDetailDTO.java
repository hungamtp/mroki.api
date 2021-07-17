package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    private float rate;
    private String categoryId;
    private String thumbnail;
    private String image1;
    private String image2;

}
