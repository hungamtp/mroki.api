package mondays.net.mroki.api.dto.customer;

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
public class RoleDTO {

    @NotBlank(message = "id is empty")
    @Size(min = 1 ,max = 1)
    private String id;

    @NotBlank(message = "name is empty")
    private String roleName;
}
