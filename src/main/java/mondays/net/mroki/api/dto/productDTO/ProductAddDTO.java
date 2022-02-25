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

    @NotBlank(message = "name is empty")
    private String name;

    @NotNull(message = "price is null")
    @Min(0)
    private Float price;

    @NotNull(message = "retail is null")
    @Min(0)
    private Float retail;

    @NotBlank(message = "description is empty")
    private String description;

    @Min(0)
    @Max(100)
    private Integer saleOff;

    private Long categoryId;

    @NotBlank(message = "thumbnail is empty")
    private String thumbnail;

    private String image1;
    private String image2;

}
