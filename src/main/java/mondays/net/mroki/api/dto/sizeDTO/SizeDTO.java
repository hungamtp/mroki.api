package mondays.net.mroki.api.dto.sizeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {

    int size;
    boolean isSoldOut;

}
