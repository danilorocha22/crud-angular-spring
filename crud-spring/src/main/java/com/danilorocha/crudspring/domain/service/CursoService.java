package com.danilorocha.crudspring.domain.service;

import com.danilorocha.crudspring.domain.model.Curso;
import com.danilorocha.crudspring.domain.repository.CursoRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CursoService {

    private CursoRepository repo;

    public Curso save(Curso curso) {
        return repo.save(curso);
    }

    public Curso update(Long id, Curso c) {
        Curso curso = repo.getReferenceById(id);
        curso.setNome(c.getNome());
        curso.setCategoria(c.getCategoria());
        return repo.saveAndFlush(curso);
    }
}
