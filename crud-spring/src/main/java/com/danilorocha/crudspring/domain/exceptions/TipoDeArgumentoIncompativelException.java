package com.danilorocha.crudspring.domain.exceptions;

import java.io.Serial;

public class TipoDeArgumentoIncompativelException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TipoDeArgumentoIncompativelException(String mensagem) {
        super("Tipo de argumento é incompatível: "+ mensagem);
    }


}
