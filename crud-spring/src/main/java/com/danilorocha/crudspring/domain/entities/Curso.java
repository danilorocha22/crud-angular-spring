package com.danilorocha.crudspring.domain.entities;

import com.danilorocha.crudspring.domain.enums.Categoria;
import com.danilorocha.crudspring.domain.enums.Status;
import com.danilorocha.crudspring.domain.enums.converters.CategoriaConversor;
import com.danilorocha.crudspring.domain.enums.converters.StatusConversor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 9)
    //@Pattern(regexp = "Front-end|Back-end")
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoriaConversor.class)
    private Categoria categoria;

    @NotNull
    @Size(min = 5, max = 7)
    //@Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    //@JsonIgnore
    @Convert(converter = StatusConversor.class)
    private Status status = Status.ATIVO;

        @OneToMany(
            mappedBy = "curso",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@JoinColumn(name = "curso_id") //provoca perda de performance
    private List<Aula> aulas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso curso)) return false;
        return Objects.equals(getId(), curso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}

