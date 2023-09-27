package com.northwind.etiya.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GlobalExceptionResponse<T> {
    private String errorType;
    private T error;
}
