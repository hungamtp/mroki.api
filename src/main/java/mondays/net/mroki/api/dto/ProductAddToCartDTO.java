package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductAddToCartDTO {

    @NotNull
    private Long id;
    @NotNull
    private int quantity;
}
