package mondays.net.mroki.api.exception;

public class DuplicatedDataException extends RuntimeException{
    public DuplicatedDataException(String message) {
        super(message);
    }
}
