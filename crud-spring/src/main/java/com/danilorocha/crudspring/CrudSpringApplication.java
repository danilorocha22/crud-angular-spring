package com.danilorocha.crudspring;

import com.danilorocha.crudspring.domain.entities.Aula;
import com.danilorocha.crudspring.domain.enums.Categoria;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.danilorocha.crudspring.domain.entities.Curso;
import com.danilorocha.crudspring.domain.repositories.CursoRepository;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner iniciarBanco(CursoRepository cursoRepository) {
        return args -> {
            cursoRepository.deleteAll();
            Curso c = new Curso();
            c.setNome("Angular com Spring");
            c.setCategoria(Categoria.FULL_STACK);

            Aula a = new Aula();
            a.setNome("Aula 45: Curso-Aulas: OneToMany");
            a.setYoutubeUrl("Nb4uxLxdvxo");
            a.setCurso(c);
            c.getAulas().add(a);

            Aula a2 = new Aula();
            a2.setNome("Aula 46: Curso-Aulas: ManyToOne");
            a2.setYoutubeUrl("MsdsxLxdvxo");
            a2.setCurso(c);
            c.getAulas().add(a2);

            cursoRepository.save(c);
        };
    }

}