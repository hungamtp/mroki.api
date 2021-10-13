package mondays.net.mroki.api.exception;

import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.responseCode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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
        return new ResponseEntity<>(errorResponse, HttpStatus.ACCEPTED);
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
