package com.ravekidd.v1.exception;
import com.ravekidd.v1.model.error.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling validation-related exceptions in a REST API.
 * This class provides methods to handle instances of MethodArgumentNotValidException
 * and other general exceptions. It returns appropriate HTTP status codes and error responses.
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with
     * HTTP status 401 (Unauthorized) and a list of error messages in the response body.
     *
     * @param ex The MethodArgumentNotValidException to be handled.
     * @return ResponseEntity with HTTP status 401 and error response.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse("Unauthorized", 401, errorMessages);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * Handles general exceptions and returns a ResponseEntity with HTTP status
     * 500 (Internal Server Error) and a general error message in the response body.
     *
     * @param ex The Exception to be handled.
     * @return ResponseEntity with HTTP status 500 and error response.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse response = new ErrorResponse("Internal Server Error", 500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

