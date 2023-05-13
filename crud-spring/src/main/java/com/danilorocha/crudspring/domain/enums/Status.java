package com.danilorocha.crudspring.domain.enums;

public enum Status {

    ATIVO("Ativo"), INATIVO("Inativo");

    private final String valor;

    Status(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return  valor;
    }

    //toString
    @Override
    public String toString() {
        return valor;
    }

}
