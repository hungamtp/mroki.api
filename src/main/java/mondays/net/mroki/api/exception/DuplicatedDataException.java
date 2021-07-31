package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;

@Setter
@Getter
public class DuplicatedDataException extends RuntimeException{
    ErrorCode errorCode;
    public DuplicatedDataException(ErrorCode message) {
        super(message.toString());
        setErrorCode(message);
    }
}
