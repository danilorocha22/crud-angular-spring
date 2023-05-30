package com.danilorocha.crudspring;

import com.danilorocha.crudspring.domain.entities.Aula;
import com.danilorocha.crudspring.domain.entities.Curso;
import com.danilorocha.crudspring.domain.enums.Categoria;
import com.danilorocha.crudspring.domain.repositories.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }
/*
    @Bean
    public DataSource actualDataSource() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        return databaseBuilder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    // use hibernate to format queries
    private static class PrettyQueryEntryCreator extends DefaultQueryLogEntryCreator {
        private final Formatter formatter = FormatStyle.BASIC.getFormatter();

        @Override
        protected String formatQuery(String query) {
            return this.formatter.format(query);
        }
    }

    @Bean
    @Primary
    public DataSource dataSource(DataSource actualDataSource) {
        // use pretty formatted query with multiline enabled
        PrettyQueryEntryCreator creator = new PrettyQueryEntryCreator();
        creator.setMultiline(true);

        SystemOutQueryLoggingListener listener = new SystemOutQueryLoggingListener();
        listener.setQueryLogEntryCreator(creator);

        return ProxyDataSourceBuilder
                .create(actualDataSource)
                .name("mysql")
                .listener(listener)
                .proxyResultSet()  // enable result proxy
                .afterMethod(executionContext -> {
                    // print out JDBC API calls to console
                    Method method = executionContext.getMethod();
                    Class<?> targetClass = executionContext.getTarget().getClass();
                    System.out.println("JDBC: " + targetClass.getSimpleName() + "#" + method.getName());
                })
                .afterQuery((execInfo, queryInfoList) -> System.out.println("Query took " + execInfo.getElapsedTime() + "msec"))
                .build();
    }*/

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