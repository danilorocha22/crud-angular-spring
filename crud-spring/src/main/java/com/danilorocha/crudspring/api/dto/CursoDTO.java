package com.danilorocha.crudspring.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CursoDTO(

        @JsonProperty("_id")
        Long id,

        @NotBlank
        @NotNull
        @Size(min = 1, max = 100)
        String nome,

        @NotNull
        @Size(min = 8, max = 9)
        //@Pattern(regexp = "Front-end|Back-end")
        String categoria,

        List<AulaDTO> aulasDTO

) {

}