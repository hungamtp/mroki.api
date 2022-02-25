package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ProductAddToCartDTO {

    @NotNull
    private Long id;

    @NotNull
    private int quantity;

    @NotNull
    private String size;
}
