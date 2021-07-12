package mondays.net.mroki.api.exception;

public class CommentConvertException extends RuntimeException{
    public CommentConvertException(Error e) {
        super(e.getMessage());
    }
}
