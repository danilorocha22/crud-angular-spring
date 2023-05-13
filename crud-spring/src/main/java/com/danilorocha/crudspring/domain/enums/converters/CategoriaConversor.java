package com.danilorocha.crudspring.domain.enums.converters;

import com.danilorocha.crudspring.domain.enums.Categoria;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Converter(autoApply = true) // Esta anotação serve para o JPA aplicar esta conversão sempre que necessário
public class CategoriaConversor implements AttributeConverter<Categoria, String> {

    @Override
    public String convertToDatabaseColumn(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return categoria.getValor();
    }

    @Override
    public Categoria convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(Categoria.values())
                .filter(c -> c.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
