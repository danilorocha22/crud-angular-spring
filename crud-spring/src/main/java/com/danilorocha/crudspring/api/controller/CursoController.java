package com.danilorocha.crudspring.api.controller;

import com.danilorocha.crudspring.domain.model.Curso;
import com.danilorocha.crudspring.domain.repository.CursoRepository;
import com.danilorocha.crudspring.domain.service.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    //@ResponseStatus(HttpStatus.ACCEPTED) //pode ser usado no lugar da classe ResponseEntity
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso curso) {
        return new ResponseEntity<>(cursoService.update(id, curso), HttpStatus.ACCEPTED);
    }
    

}
