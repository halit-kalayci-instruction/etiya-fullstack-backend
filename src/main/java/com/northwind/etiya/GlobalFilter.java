package com.northwind.etiya;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,DELETE,PUT");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        String path = ((HttpServletRequest) request).getRequestURI();
        String x = req.getHeader("Authorization");
        if(path.contains("swagger") || path.contains("api-docs")){
            chain.doFilter(request, response);
            return;
        }
        if(( x==null || !x.equals("Bearer Etiya") )){
            com.northwind.etiya.ErrorResponse errorResponse = new com.northwind.etiya.ErrorResponse();
            errorResponse.setCode(401);
            errorResponse.setMessage("Unauthorized Access");
            byte[] responseToSend = restResponseBytes(errorResponse);
            ((HttpServletResponse) response).setHeader("Content-Type", "application/json");

            ((HttpServletResponse) response).setStatus(401);
            response.getOutputStream().write(responseToSend);
        }else{
            chain.doFilter(request, response);
        }
    }

    private byte[] restResponseBytes(com.northwind.etiya.ErrorResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }
    // other methods 
}