package com.generation.farmasys.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden //evita que o springdoc analise essa classe para evitar imcompatibilidades
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
    	
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ex.getStatusCode().value());
        errorResponse.setMessage(ex.getReason());

        // Retorna a resposta com o status apropriado
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}