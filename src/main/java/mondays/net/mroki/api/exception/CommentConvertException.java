package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;


@Getter
@Setter
public class CommentConvertException extends RuntimeException{

    ErrorCode errorCode;
    public CommentConvertException(ErrorCode e) {

        super(e.toString());
        setErrorCode(e);
    }
}
