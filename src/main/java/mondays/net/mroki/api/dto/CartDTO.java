package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {

    @NotBlank(message = "cart missing customer_id")
    private Long customer_id;

    @NotBlank
    private HashMap<ProductDTO, Integer> product = new HashMap<>();
}
