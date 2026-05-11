package com.abbtech.iamservice.common.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.abbtech.iamservice.common.exception.base.BaseErrorEnum;
import com.abbtech.iamservice.common.exception.base.BaseErrorResponseDTO;
import com.abbtech.iamservice.common.exception.base.BaseException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponseDTO> handleConstraintViolationException(MethodArgumentNotValidException ex,
                                                                                   WebRequest webRequest) {


        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new BaseErrorResponseDTO(BaseErrorEnum.BASE_VALIDATION_ERROR.getErrorCode(),
                BaseErrorEnum.BASE_BUSINESS_ERROR.getMessage(), webRequest.getContextPath(),
                LocalDateTime.now().toString(), BaseErrorEnum.BASE_VALIDATION_ERROR.getHttpStatus(), errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseErrorResponseDTO> handleBaseException(BaseException ex,
                                                                    WebRequest webRequest) {
        log.error("BaseException: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(new BaseErrorResponseDTO(ex.baseErrorService.getErrorCode(),
                ex.baseErrorService.getMessage(), webRequest.getContextPath(), LocalDateTime.now().toString(),
                ex.baseErrorService.getHttpStatus()), HttpStatusCode.valueOf(ex.baseErrorService.getHttpStatus())
        );
    }
}
