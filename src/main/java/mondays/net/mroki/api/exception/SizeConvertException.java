package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;

@Getter
@Setter
public class SizeConvertException extends RuntimeException{
    private ErrorCode errorCode;
    public SizeConvertException(ErrorCode message) {
        super(message.toString());
        setErrorCode(message);
    }
}
