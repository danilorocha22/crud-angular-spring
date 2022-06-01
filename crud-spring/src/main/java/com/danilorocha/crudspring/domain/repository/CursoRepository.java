package com.danilorocha.crudspring.domain.repository;

import com.danilorocha.crudspring.domain.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {


}
