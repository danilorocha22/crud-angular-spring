package com.danilorocha.crudspring.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class Curso {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 12, nullable = false)
    private String categoria;

    @Column(length = 10, nullable = false)
    private String status;
}
