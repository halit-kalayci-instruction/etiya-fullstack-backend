package com.northwind.etiya;

import lombok.Data;

@Data
public class ErrorResponse {
    private int code;
    private String message;
}
