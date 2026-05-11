package com.abbtech.payment.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MarketPlaceException.class)
    public ResponseEntity<BaseErrorResponseDTO> handleMarketPlaceException(MarketPlaceException exception) {
        var error = exception.getError();
        return ResponseEntity
                .status(error.getStatus())
                .body(new BaseErrorResponseDTO(error.getMessage(), error.getStatus().value(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponseDTO> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Validation failed");
        return ResponseEntity.badRequest().body(new BaseErrorResponseDTO(message, 400, LocalDateTime.now()));
    }
}
