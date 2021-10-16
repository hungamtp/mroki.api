package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity dataNotFoundException(DuplicatedDataException ex, WebRequest request) {
        ResponseDTO errorResponse = new ResponseDTO(null, null, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity dataNotFoundException(IllegalStateException ex, WebRequest request) {
        ResponseDTO errorResponse = new ResponseDTO(null, null, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity wrongUsernameOrPassword(AuthException ex, WebRequest request) {
        ResponseDTO errorResponse = new ResponseDTO(null, null, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity dataNotFound(DataNotFoundException ex, WebRequest request) {
        ResponseDTO errorResponse = new ResponseDTO(null, null, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return errors;
//    }


}
