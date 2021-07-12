package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentAddDTO {

    @NotNull(message = "productId is null")
    private Long productId;

    @NotNull(message = "customerId is null")
    private Long customerId;

    private String content;

    @NotNull(message = "rate is null")
    @Min(1)
    @Max(5)
    private Integer rate;
}
