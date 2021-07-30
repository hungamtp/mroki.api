package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.responseCode.ErrorCode;

public class CommentConvertException extends RuntimeException{

    public CommentConvertException(ErrorCode e) {
        super(e.toString());
    }
}
