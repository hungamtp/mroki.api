package mondays.net.mroki.api.dto.size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SizeUpdateDTO {

    @NotNull
    private Long productId;

    @NotNull
    @Min(35)
    @Max(48)
    private int size;

    @NotNull
    @Min(0)
    private int quantity;
}
