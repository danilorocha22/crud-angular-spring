package com.danilorocha.crudspring.domain.enums.converters;

import com.danilorocha.crudspring.domain.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true) // Esta anotação serve para o JPA aplicar esta conversão sempre que necessário
public class StatusConversor implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValor();
    }

    @Override
    public Status convertToEntityAttribute(String valor) {
        if (valor == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getValor().equals(valor))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
