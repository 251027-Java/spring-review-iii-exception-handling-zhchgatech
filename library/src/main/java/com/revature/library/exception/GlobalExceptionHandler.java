package com.revature.library.exception;


import com.revature.library.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler - TODO: Complete the exception handlers
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO: Handle BookNotFoundException
     @ExceptionHandler(BookNotFoundException.class)
     public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex) {
         ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
     }

    // TODO: Handle BookNotAvailableException
     @ExceptionHandler(BookNotAvailableException.class)
     public ResponseEntity<ErrorResponse> handleBookNotAvailable(BookNotAvailableException ex) {
         ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value(), LocalDateTime.now());
         return new ResponseEntity<>(error, HttpStatus.CONFLICT);
     }

    // TODO: Handle validation errors
     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
         StringBuilder sb = new StringBuilder();
         for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
             sb.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
         }
         ErrorResponse error = new ErrorResponse(sb.toString(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
         return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
     }

//    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("status", status.value());
//        body.put("error", status.getReasonPhrase());
//        body.put("message", message);
//        return new ResponseEntity<>(body, status);
//    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        ErrorResponse error = new ErrorResponse(
                message,
                status.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, status);
    }

}
