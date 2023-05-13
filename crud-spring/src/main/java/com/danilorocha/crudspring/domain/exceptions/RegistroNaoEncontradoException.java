package com.danilorocha.crudspring.domain.exceptions;

import java.io.Serial;

public class RegistroNaoEncontradoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    
    public RegistroNaoEncontradoException(Long id) {
        super("Registro não encontrado com Id: "+ id);
    }
        
}
