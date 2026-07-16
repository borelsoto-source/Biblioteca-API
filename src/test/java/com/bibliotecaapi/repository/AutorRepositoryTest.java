package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository repository;

    @Test
    void salvarAutor(){
        Autor autor = new Autor();
        autor.setNome("Rafael Montes");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1990, 9, 22));

        repository.save(autor);
    }
}

