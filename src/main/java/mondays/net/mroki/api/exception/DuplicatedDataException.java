package mondays.net.mroki.api.exception;

import lombok.Getter;
import lombok.Setter;
import mondays.net.mroki.api.responseCode.ErrorCode;

@Setter
@Getter
public class DuplicatedDataException extends RuntimeException{

    public DuplicatedDataException(String message) {
        super(message);
    }
}
