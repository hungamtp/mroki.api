package mondays.net.mroki.api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(DataNotFoundException.class)
//    public ResponseEntity dataNotFoundException(DataNotFoundException ex, WebRequest request) {
//        ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
}
