package com.danilorocha.crudspring.api.dto.mapper;

import com.danilorocha.crudspring.api.dto.AulaDTO;
import com.danilorocha.crudspring.api.dto.CursoDTO;
import com.danilorocha.crudspring.domain.entities.Curso;
import com.danilorocha.crudspring.domain.enums.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public CursoDTO converterToCursoDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        List<AulaDTO> aulasDTO = curso.getAulas().stream()
                .map(aula -> new AulaDTO(aula.getId(), aula.getNome(), aula.getYoutubeUrl()))
                .toList();

        return new CursoDTO(
                curso.getId(),
                curso.getNome(),
                curso.getCategoria().getValor(),
                aulasDTO);
    }

    public Curso converterToCurso(CursoDTO cursoDTO) {
        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();
        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());
        }
        curso.setNome(cursoDTO.nome());
        curso.setCategoria(converterCategoryValue(cursoDTO.categoria()));

        /*List<Aula> aulas = cursoDTO.aulasDTO().stream()
                .map(aulaDTO -> {
                    Aula aula = new Aula();
                    aula.setId(aulaDTO.id());
                    aula.setNome(aulaDTO.nome());
                    aula.setYoutubeUrl(aulaDTO.youtubeUrl());
                    aula.setCurso(converterToCurso(aulaDTO.cursoDTO()));
                    return aula;
                })
                .toList();

        curso.setAulas(aulas);*/
        return curso;
    }

    public Categoria converterCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-End" -> Categoria.FRONT_END;
            case "Back-End" -> Categoria.BACK_END;
            case "Mobile" -> Categoria.MOBILE;
            case "Full-Stack" -> Categoria.FULL_STACK;
            case "Banco de Dados" -> Categoria.BANCO_DE_DADOS;
            default -> throw new IllegalArgumentException("Categoria Inválida: " + value);
        };
    }


}
