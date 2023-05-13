package com.danilorocha.crudspring.domain.enums;

public enum Categoria {
    FRONT_END("Front-End"),
    BACK_END("Back-End"),
    MOBILE("Mobile"),
    FULL_STACK("Full-Stack"),
    BANCO_DE_DADOS("Banco de dados");

    private final String valor;

    Categoria(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    //toString
    @Override
    public String toString() {
        return valor;
    }

}
