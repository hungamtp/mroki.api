package mondays.net.mroki.api.dto.categoryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    @NotBlank(message = "Category id must be 1 char")
    @Size(max = 1, min = 1)
    private Long id;

    private String parentId;

    @NotBlank(message = "name is empty")
    @Size(max = 25, min = 1)
    private String name;
}
