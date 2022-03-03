package mondays.net.mroki.api.dto.categoryDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryDTO {
    private Long id;
    private String name;
    private boolean isDeleted;
}
