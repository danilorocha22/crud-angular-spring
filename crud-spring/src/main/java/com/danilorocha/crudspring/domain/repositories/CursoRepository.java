package com.danilorocha.crudspring.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilorocha.crudspring.domain.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {


}
