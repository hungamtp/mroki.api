package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.responseCode.ErrorCode;

public class CategoryConverterException extends RuntimeException{

    public CategoryConverterException(ErrorCode message) {
        super(message.toString());
    }
}
