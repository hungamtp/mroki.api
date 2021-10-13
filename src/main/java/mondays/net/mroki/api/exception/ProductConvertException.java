package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;

@Getter
@Setter
public class ProductConvertException extends RuntimeException {



    public ProductConvertException(String message) {
        super(message);
    }
}
