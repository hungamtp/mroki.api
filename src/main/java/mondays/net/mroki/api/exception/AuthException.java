package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;
import org.springframework.security.core.AuthenticationException;

@Setter
@Getter
public class AuthException extends AuthenticationException {
    private ErrorCode errorCode;
    public AuthException(ErrorCode message) {
        super(message.toString());
        setErrorCode(message);
    }
}
