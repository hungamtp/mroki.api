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

    private String name;

    private float price;

    private float retail;

    private String description;

    private Integer saleOff;

    private Long categoryId;

    private String thumbnail;

    LocalDate updatedDate;

    private String image1;
    private String image2;
}
