package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.responseCode.ErrorCode;

public class ProductConvertException extends RuntimeException {




    public ProductConvertException(ErrorCode message) {
        super(message.toString());
    }
}
