package com.devsuperior.dscatalog.resources.exceptions;

import com.devsuperior.dscatalog.services.exceptions.DatabaseIntegrityException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = createStandardError(request, status, "Resource not found", ex.getMessage());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseIntegrityException.class)
    public ResponseEntity<StandardError> databaseIntegrityException(DatabaseIntegrityException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = createStandardError(request, status, "Database integrity violation", ex.getMessage());
        return ResponseEntity.status(status).body(standardError);
    }

    private StandardError createStandardError(HttpServletRequest request, HttpStatus status, String error, String message) {
        return StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(message)
                .path(request.getRequestURI())
                .build();
    }
}
