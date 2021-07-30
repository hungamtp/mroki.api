package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.responseCode.ErrorCode;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(ErrorCode message) {
        super(message.toString());
    }
}
