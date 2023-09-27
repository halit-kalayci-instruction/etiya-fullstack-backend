package com.northwind.etiya.exceptions;

import com.northwind.etiya.exceptions.types.UnauthorizedException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ExceptionHandlers {
    @ExceptionHandler({ UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException() {
        //
        System.out.println("Exce");
        return "Unauthorized";
    }
}
