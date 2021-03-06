package pl.teo.crm.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiNotFoundException.class)
    public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = ApiForbiddenException.class)
    public ResponseEntity<Object> handleApiForbiddenException(ApiForbiddenException e) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = ApiBadRequestException.class)
    public ResponseEntity<Object> handleApiBadRequestException(ApiBadRequestException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions( MethodArgumentNotValidException ex) {
        String strErrors = ex.getBindingResult().getAllErrors().stream().map(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            return String.format("%s: %s", fieldName, errorMessage);
        }).collect(Collectors.joining(", "));

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                strErrors,
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, status);
    }
}
