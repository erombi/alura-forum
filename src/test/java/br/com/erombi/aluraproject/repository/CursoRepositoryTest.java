package br.com.erombi.aluraproject.repository;

import br.com.erombi.aluraproject.model.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    private String cursoExistente;
    private String cursoInexistente;

    @BeforeEach
    void setUp() throws Exception {
        cursoExistente = "Spring Boot";
        cursoInexistente = "Spring";
    }

    @Test
    public void shouldLoadCourseWhenFindByNome() {
        Curso curso = repository.findByNome(cursoExistente);

        Assertions.assertNotNull(curso);
        Assertions.assertEquals(cursoExistente, curso.getNome());
    }

    @Test
    public void shouldThrowExceptionWhenFindByNomeNonExistsNome() {
        Curso curso = repository.findByNome(cursoInexistente);

        Assertions.assertNull(curso);
    }
}
