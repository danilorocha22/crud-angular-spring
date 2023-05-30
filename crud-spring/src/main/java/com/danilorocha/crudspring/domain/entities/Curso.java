package com.danilorocha.crudspring.domain.entities;

import com.danilorocha.crudspring.domain.enums.Categoria;
import com.danilorocha.crudspring.domain.enums.Status;
import com.danilorocha.crudspring.domain.enums.converters.CategoriaConversor;
import com.danilorocha.crudspring.domain.enums.converters.StatusConversor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
@Table(name = "cursos")
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE cursos SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Curso {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    /*@NotBlank
    @NotNull*/
    /*@Size(min = 1, max = 100)*/
    @Column(length = 100, nullable = false)
    private String nome;

    /*@NotBlank
    @NotNull*/
    /*@Size(min = 8, max = 9)*/
    //@Pattern(regexp = "Front-end|Back-end")
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoriaConversor.class)
    private Categoria categoria;

    /*@NotNull*/
    /*@Size(min = 5, max = 7)*/
    //@Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    //@JsonIgnore
    @Convert(converter = StatusConversor.class)
    private Status status = Status.ATIVO;

    @OneToMany(
            mappedBy = "curso",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@JoinColumn(name = "curso_id") //provoca perda de desempenho
    private List<Aula> aulas = new ArrayList<>();

    public void addAula(Aula aula) {
        this.aulas.add(aula);
        aula.setCurso(this);
    }

    public void removeAula(Aula aula) {
        this.aulas.remove(aula);
        aula.setCurso(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso c)) return false;
        return id !=  null && id.equals(c.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}

