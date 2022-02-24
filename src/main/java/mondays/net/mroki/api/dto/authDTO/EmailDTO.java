package mondays.net.mroki.api.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    @NotBlank(message = "Email is empty")
    private String email;
    private String password;
}