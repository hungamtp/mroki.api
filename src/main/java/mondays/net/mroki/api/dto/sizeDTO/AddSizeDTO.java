package mondays.net.mroki.api.dto.sizeDTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSizeDTO {
    private Long productId;
    private String size;
    private int quantity;
}
