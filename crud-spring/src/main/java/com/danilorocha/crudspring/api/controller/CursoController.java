package com.danilorocha.crudspring.api.controller;

import com.danilorocha.crudspring.api.model.CursoModel;
import com.danilorocha.crudspring.domain.entities.Curso;
import com.danilorocha.crudspring.domain.repositories.CursoRepository;
import com.danilorocha.crudspring.domain.services.CursoService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final CursoService cursoService;

    // @RequestMapping(method = RequestMethod.GET) mesma coisa que a anotação abaixo
    @GetMapping
    public ResponseEntity<List<Curso>> cursos() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> curso(@PathVariable Long id) {
        return cursoService.getCurso(id)
                .map(curso -> ResponseEntity.ok().body(curso))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Curso> createCurso(@RequestBody CursoModel cursoModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoModel));
    }

    // @ResponseStatus(HttpStatus.ACCEPTED) //pode ser usado no lugar da classe
    // ResponseEntity
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody CursoModel cursoModel) {
        return new ResponseEntity<>(cursoService.update(id, cursoModel), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public void delelteCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }

}
