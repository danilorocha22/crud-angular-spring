package com.danilorocha.crudspring;

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
            c.setCategoria("front-end");
            cursoRepository.save(c);
        };
    }

}
