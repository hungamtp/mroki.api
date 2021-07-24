package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductUpdateDTO {
    private Long id;

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

    @NotBlank(message = "cate is empty")
    private String categoryId;

    @NotBlank(message = "thumbnail is empty")
    private String thumbnail;

    LocalDate createDate;

    private String image1;
    private String image2;
}
