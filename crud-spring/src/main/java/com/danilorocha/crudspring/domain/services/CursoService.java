package com.danilorocha.crudspring.domain.services;

import com.danilorocha.crudspring.api.dto.CursoDTO;
import com.danilorocha.crudspring.api.dto.mapper.CursoMapper;
import com.danilorocha.crudspring.domain.exceptions.RegistroNaoEncontradoException;
import com.danilorocha.crudspring.domain.repositories.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Validated
@AllArgsConstructor
@Service
public class CursoService {

    private CursoRepository repo;
    private CursoMapper mapper;

    public List<CursoDTO> listarCursos() {
        return repo.findAll().stream().map(mapper::converterToCursoDTO).toList();
    }

    public CursoDTO buscarCurso(@NotNull @Positive Long id) {
        return repo.findById(id).map(mapper::converterToCursoDTO).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public CursoDTO criarCurso(@Valid @NotNull CursoDTO cursoDTO) {
        return mapper.converterToCursoDTO(repo.save(mapper.converterToCurso(cursoDTO)));
    }

    public CursoDTO atualizarCurso(@NotNull @Positive Long id, @RequestBody @Valid @NotNull
    CursoDTO cursoDTO) {
        return repo.findById(id).map(curso -> {
            curso.setNome(cursoDTO.nome());
            curso.setCategoria(mapper.converterCategoryValue(cursoDTO.categoria()));
            return mapper.converterToCursoDTO(repo.saveAndFlush(curso));
        }).orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    public void removerCurso(@NotNull @Positive Long id) {
        repo.delete(repo.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id)));
        /*repo.findById(id)
                .map(cursoEncontrado -> {
                    repo.delete(cursoEncontrado);
                    return true;
                }).orElseThrow(() -> new RegistroNaoEncontradoException(id));*/
    }

    /**
     * public Curso update(Long id, CursoModel cursoModel) {
     * Curso curso = repo.getReferenceById(id);
     * curso.setNome(cursoModel.getNome());
     * curso.setCategoria(cursoModel.getCategoria());
     * return repo.saveAndFlush(curso);
     * }
     */
}