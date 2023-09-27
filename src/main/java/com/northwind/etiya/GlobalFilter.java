package com.northwind.etiya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northwind.etiya.exceptions.types.UnauthorizedException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
@Order(1)
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String x = req.getHeader("Authorization");
        if(!x.equals("Bearer Etiya")){
            com.northwind.etiya.ErrorResponse errorResponse = new com.northwind.etiya.ErrorResponse();
            errorResponse.setCode(401);
            errorResponse.setMessage("Unauthorized Access");
            byte[] responseToSend = restResponseBytes(errorResponse);
            ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
            ((HttpServletResponse) response).setStatus(401);
            response.getOutputStream().write(responseToSend);
            return;
        }
        chain.doFilter(request, response);

    }

    private byte[] restResponseBytes(com.northwind.etiya.ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
    // other methods 
}