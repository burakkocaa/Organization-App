package io.digitopia.organization.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
	
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException notFoundException) {
        List<String> detail = new ArrayList<>();
        detail.add(notFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Not Found Exception", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<?> notNullException(NotNullException notNullException) {
        List<String> detail = new ArrayList<>();
        detail.add(notNullException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Not Null Exception", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequest(BadRequest badRequest) {
        List<String> detail = new ArrayList<>();
        detail.add(badRequest.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Bad Request Exception", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
}
