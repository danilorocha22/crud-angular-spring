package com.danilorocha.crudspring.domain.repositories;

import com.danilorocha.crudspring.domain.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {


}
