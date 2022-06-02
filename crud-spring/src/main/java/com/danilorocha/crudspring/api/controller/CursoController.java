package com.danilorocha.crudspring.api.controller;

import com.danilorocha.crudspring.domain.model.Curso;
import com.danilorocha.crudspring.domain.repository.CursoRepository;
import com.danilorocha.crudspring.domain.service.CursoService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@RequestMapping("cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final CursoService cursoService;

    //@RequestMapping(method = RequestMethod.GET) mesma coisa que a anotação abaixo
    @GetMapping
    public ResponseEntity<List<Curso>> list() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        return new ResponseEntity<>(cursoService.save(curso), HttpStatus.CREATED);
    }
    

}
