package com.myblog.blogapp.exception;

import com.myblog.blogapp.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Suvorov
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlePostNotFoundException(
       PostNotFoundException postNotFoundException,
       WebRequest webRequest
    ) {
    ErrorDetails errorDetails = new ErrorDetails(
            new Date(),
            postNotFoundException.getMessage(),
            webRequest.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCommentNotFoundException(
            CommentNotFoundException commentNotFoundException,
            WebRequest webRequest
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                commentNotFoundException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) { 
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
