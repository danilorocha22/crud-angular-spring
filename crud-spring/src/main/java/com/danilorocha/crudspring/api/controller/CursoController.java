package com.danilorocha.crudspring.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cursos")
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

    @DeleteMapping("/{id}")
    public void delelteCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }

}
