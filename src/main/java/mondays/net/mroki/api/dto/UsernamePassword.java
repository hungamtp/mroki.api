package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// this class to cast data from request (username , password)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsernamePassword {

    private String username;
    private String password;
}
