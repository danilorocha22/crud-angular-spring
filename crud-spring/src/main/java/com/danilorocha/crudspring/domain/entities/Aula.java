package com.danilorocha.crudspring.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "aulas")
@RequiredArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotBlank
    @NotNull
    @Size(min = 3, max = 100)*/
    @Column(length = 100, nullable = false)
    private String nome;

    /*@NotBlank
    @NotNull
    @Size(min = 8, max = 15)*/
    @Column(length = 15, nullable = false)
    private String youtubeUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id" ,nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Curso curso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aula a)) return false;
        return id != null && id.equals(a.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
