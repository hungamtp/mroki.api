package mondays.net.mroki.api.dto.product;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ProductAddToCartDTO {

    @NotNull
    private Long id;

    @NotNull
    private int quantity;

    @NotNull
    @Max(50)
    @Min(35)
    private int size;
}
