package mondays.net.mroki.api.dto.sizeDTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSizeDTO {
    private Long productId;
    private String size;
    private int quantity;
}
