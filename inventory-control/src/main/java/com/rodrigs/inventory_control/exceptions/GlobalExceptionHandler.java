package com.rodrigs.inventory_control.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Rest ControllerAdvice é uma anotação do Spring que permite a criação de um manipulador global de exceções para todos os controladores REST na aplicação.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // O método handleProductNotFound é anotado com @ExceptionHandler(ProductNotFoundException.class),
    //  indicando que ele será chamado quando uma exceção do tipo ProductNotFoundException for lançada em qualquer controlador REST da aplicação.
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
