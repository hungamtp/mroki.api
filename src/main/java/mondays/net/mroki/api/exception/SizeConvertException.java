package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.responseCode.ErrorCode;

public class SizeConvertException extends RuntimeException{
    public SizeConvertException(ErrorCode message) {
        super(message.toString());
    }
}
