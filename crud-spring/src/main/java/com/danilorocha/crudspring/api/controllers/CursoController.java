package com.danilorocha.crudspring.api.controllers;

import com.danilorocha.crudspring.api.dto.CursoDTO;
import com.danilorocha.crudspring.domain.services.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cursos")
public class CursoController {

    //private final CursoRepository cursoRepository;
    private final CursoService cursoService;

    // @RequestMapping(method = RequestMethod.GET) mesma coisa que a anotação abaixo
    @GetMapping
    public List<CursoDTO> listarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/{id}")
    public CursoDTO buscarCurso(@PathVariable @NotNull @Positive Long id) {
        return cursoService.buscarCurso(id);
                //.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDTO criarCurso(@RequestBody @Valid @NotNull CursoDTO cursoDTO) {
        return cursoService.criarCurso(cursoDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CursoDTO atualizarCurso(@PathVariable @NotNull @Positive Long id, @Valid @RequestBody @NotNull CursoDTO cursoDTO) {
        return cursoService.atualizarCurso(id, cursoDTO);
                //.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCurso(@PathVariable @NotNull @Positive Long id) {
        cursoService.removerCurso(id);
    }

    /*
     * @ResponseStatus(HttpStatus.ACCEPTED) //pode ser usado no lugar da classe
     * ResponseEntity
     * 
     * @PutMapping("/{id}")
     * public ResponseEntity<Curso> atualizarCurso(@PathVariable Long
     * id, @RequestBody CursoModel cursoModel) {
     * return new ResponseEntity<>(cursoService.update(id, cursoModel),
     * HttpStatus.ACCEPTED);
     * }
     */

    /**
     * @PutMapping("/{id}")
     * public ResponseEntity<Curso> atualizarCurso(@PathVariable @NotNull @Positive
     * Long id, @Valid @RequestBody CursoModel cursoModel) {
     * return cursoRepository.findById(id)
     * .map(cursoEncontrado -> {
     * cursoEncontrado.setNome(cursoModel.getNome());
     * cursoEncontrado.setCategoria(cursoModel.getCategoria());
     * Curso cursoAtualizado = cursoRepository.saveAndFlush(cursoEncontrado);
     * return ResponseEntity.ok().body(cursoAtualizado);
     * 
     * })
     * .orElse(ResponseEntity.notFound().build());
     * }
     */

}