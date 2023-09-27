package com.northwind.etiya.exceptions;

import com.northwind.etiya.exceptions.types.BusinessException;
import com.northwind.etiya.exceptions.types.UnauthorizedException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName,errorMessage);
        });
        return new GlobalExceptionResponse<>("ValidationException", errors);
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public GlobalExceptionResponse<String> handleBusinessException(BusinessException ex){
        return new GlobalExceptionResponse<>("BusinessException", ex.getMessage());
    }

}
