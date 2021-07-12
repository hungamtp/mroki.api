package mondays.net.mroki.api.exception;

public class ConvertProductException extends RuntimeException {

    public ConvertProductException(Error e) {
        super(e.getMessage());
    }


}
