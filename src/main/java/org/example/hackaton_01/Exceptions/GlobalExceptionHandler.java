package org.example.hackaton_01.Exceptions;
import org.apache.coyote.BadRequestException;
import org.example.hackaton_01.DTOS.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex) {
        String path = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return new ResponseEntity<>(new ErrorResponse("CONFLICT", ex.getMessage(), path), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        String path = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return new ResponseEntity<>(new ErrorResponse("NOT_FOUND", ex.getMessage(), path), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        String path = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return new ResponseEntity<>(new ErrorResponse("BAD_REQUEST", ex.getMessage(), path), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedDomainException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedDomainException ex) {
        String path = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return new ResponseEntity<>(new ErrorResponse("FORBIDDEN", ex.getMessage(), path), HttpStatus.FORBIDDEN);
    }
}