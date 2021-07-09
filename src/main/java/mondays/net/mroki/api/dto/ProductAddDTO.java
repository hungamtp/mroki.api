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
public class ProductAddDTO {

    @NotBlank(message = "name is empty")
    private String name;

    @NotBlank(message = "price is empty")
    @Size(min=0)
    private float price;

    @NotBlank(message = "retail is empty")
    @Size(min =0)
    private float retail;

    @NotBlank(message = "description is empty")
    private String description;

    @Size(min =0)
    private int quantity;

    @Size(min = 0 , max = 100)
    private int saleOff;

    @NotBlank(message = "cate is empty")
    private String categoryId;

    @NotBlank(message = "thumbnail is empty")
    private String thumbnail;

    private String image1;
    private String image2;





}
