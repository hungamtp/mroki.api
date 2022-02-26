package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductAddDTO {

    private String name;

    private Float price;

    private Float retail;

    private String description;

    private Integer saleOff;

    private Long categoryId;

    private String thumbnail;

    private String image1;
    private String image2;

}
