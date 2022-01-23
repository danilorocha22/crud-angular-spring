package com.danilorocha.crudspring.controller;

import com.danilorocha.crudspring.model.Curso;
import com.danilorocha.crudspring.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    //@RequestMapping(method = RequestMethod.GET) mesma coisa que a anotação abaixo
    @GetMapping
    public List<Curso> list() {
        return cursoRepository.findAll();
    }

}
