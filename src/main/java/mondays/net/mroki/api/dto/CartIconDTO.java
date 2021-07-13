package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartIconDTO {
    private Long cartId;
    private int count;
}
