package com.danilorocha.crudspring.domain.services;

import com.danilorocha.crudspring.api.model.CursoModel;
import com.danilorocha.crudspring.domain.entities.Curso;
import com.danilorocha.crudspring.domain.repositories.CursoRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CursoService {

    private CursoRepository repo;

    public Curso save(CursoModel cursoModel) {
        Curso curso = new Curso();
        curso.setId(cursoModel.getId());
        curso.setNome(cursoModel.getNome());
        curso.setCategoria(cursoModel.getCategoria());
        return repo.save(curso);
    }

    public Curso update(Long id, CursoModel cursoModel) {
        Curso curso = repo.getReferenceById(id);
        curso.setNome(cursoModel.getNome());
        curso.setCategoria(cursoModel.getCategoria());
        return repo.saveAndFlush(curso);
    }

    public Optional<Curso> getCurso(Long id) {
        return repo.findById(id);
    }

}
