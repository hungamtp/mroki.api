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

    @NotBlank(message = "name is null")
    private String name;

    @NotBlank(message = "price is null")
    @Size(min=0)
    private float price;

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

    @NotBlank(message = "thumbnail is null")
    private String thumbnail;

    private String image1;
    private String image2;





}
