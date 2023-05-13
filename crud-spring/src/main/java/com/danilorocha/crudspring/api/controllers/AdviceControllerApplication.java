package com.danilorocha.crudspring.api.controllers;

import com.danilorocha.crudspring.domain.exceptions.RegistroNaoEncontradoException;
import com.danilorocha.crudspring.domain.exceptions.TipoDeArgumentoIncompativelException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceControllerApplication {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public String registroNaoEncontradoHandler(RegistroNaoEncontradoException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TipoDeArgumentoIncompativelException.class)
    public String tipoDeArgumentoIncompativelHandler(TipoDeArgumentoIncompativelException ex) {
        return ex.getMessage();
    }

}
