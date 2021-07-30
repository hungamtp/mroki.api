package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.responseCode.ErrorCode;

public class DuplicatedDataException extends RuntimeException{
    public DuplicatedDataException(ErrorCode message) {
        super(message.toString());
    }
}
