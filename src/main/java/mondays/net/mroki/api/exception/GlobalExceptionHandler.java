package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity dataNotFoundException(DuplicatedDataException ex, WebRequest request) {
        ResponseDTO errorResponse = new ResponseDTO(null, null , ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity wrongUsernameOrPassword(AuthenticationException ex , WebRequest request){
        ResponseDTO errorResponse = new ResponseDTO(null, null , ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }



}
