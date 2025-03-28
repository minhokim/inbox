package kr.re.bgp.jpademo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException e) {
        ApiError error = new ApiError(500, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
