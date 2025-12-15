package com.example._Do.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Centralized exception handling component for the application.
 * <p>
 * Annotated with RestControllerAdvice, this class acts as an interceptor
 * for exceptions thrown by Controllers. It transforms specific application exceptions
 * into structured {@link ErrorResponse} objects.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where a requested resource is not found in the persistence layer.
     * <p>
     * Returns a 404 Not Found status code with a descriptive message.
     * </p>
     *
     * @param ex      The captured {@link EntityNotFoundException}.
     * @param request The HTTP request that triggered the exception.
     * @return A {@link ResponseEntity} containing the structured error details.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles validation failures for request bodies annotated with {@code @Valid}.
     * <p>
     * Aggregates multiple field errors into a single message string and returns
     * a 400 Bad Request status code.
     * </p>
     *
     * @param ex      The exception containing binding results and field errors.
     * @param request The HTTP request that triggered the validation failure.
     * @return A {@link ResponseEntity} containing the validation error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        // Collect all validation errors into a single comma-separated string
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse error = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}