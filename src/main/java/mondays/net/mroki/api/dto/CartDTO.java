package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO implements Serializable {

    private Long customer_id;

    private HashMap<ProductDTO, Integer> product = new HashMap<>();
}
