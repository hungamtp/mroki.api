package mondays.net.mroki.api.dto.customerDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;
    private String username;
    private String avatar;
    private String phone;
    private String email;
    private String roleName;
    private boolean isVerifiedEmail;
    private boolean isActive;
}
