package com.danilorocha.crudspring.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE Curso SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Curso {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 9)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 10, nullable = false)
    private String categoria;

    @NotNull
    @Size(min = 5, max = 7)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

}

