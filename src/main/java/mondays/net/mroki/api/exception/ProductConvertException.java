package mondays.net.mroki.api.exception;

public class ProductConvertException extends RuntimeException {

    public ProductConvertException(Error e) {
        super(e.getMessage());
    }


}
