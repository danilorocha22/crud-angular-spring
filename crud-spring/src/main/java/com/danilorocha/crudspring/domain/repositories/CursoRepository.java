package com.danilorocha.crudspring.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danilorocha.crudspring.domain.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {


}
