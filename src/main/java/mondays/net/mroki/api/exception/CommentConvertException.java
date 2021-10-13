package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;


@Getter
@Setter
public class CommentConvertException extends RuntimeException{


    public CommentConvertException(String e) {

        super(e);
    }
}
