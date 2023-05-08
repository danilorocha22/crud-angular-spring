package com.danilorocha.crudspring.api.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilorocha.crudspring.api.model.CursoModel;
import com.danilorocha.crudspring.domain.entities.Curso;
import com.danilorocha.crudspring.domain.repositories.CursoRepository;
import com.danilorocha.crudspring.domain.services.CursoService;

import lombok.AllArgsConstructor;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final CursoService cursoService;

    // @RequestMapping(method = RequestMethod.GET) mesma coisa que a anotação abaixo
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(curso -> ResponseEntity.ok().body(curso))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Curso> criarCurso(@RequestBody @Valid CursoModel cursoModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoModel));
    }

    /* @ResponseStatus(HttpStatus.ACCEPTED) //pode ser usado no lugar da classe ResponseEntity
     * @PutMapping("/{id}")
     * public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody CursoModel cursoModel) {
     *      return new ResponseEntity<>(cursoService.update(id, cursoModel), HttpStatus.ACCEPTED);
     * }
     */

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable @NotNull @Positive  Long id,
                                                @Valid @RequestBody CursoModel cursoModel) {
        return cursoRepository.findById(id)
        .map(cursoEncontrado -> {
            cursoEncontrado.setNome(cursoModel.getNome());
            cursoEncontrado.setCategoria(cursoModel.getCategoria());
            Curso cursoAtualizado = cursoRepository.saveAndFlush(cursoEncontrado);
            return ResponseEntity.ok().body(cursoAtualizado);

        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
        .map(cursoEncontrado -> {
            cursoRepository.delete(cursoEncontrado);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }
 
}