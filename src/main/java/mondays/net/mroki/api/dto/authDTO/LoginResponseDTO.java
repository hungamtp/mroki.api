package mondays.net.mroki.api.dto.authDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String jwt;
    private String username;
    private Long userId;
    private String role;
    private String avatar;
    private boolean isActive;
    private String phone;
    private String address;
    private String name;
    private String email;

}
