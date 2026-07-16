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
        autor.setNome("Gabriel Garcia Marques");
        autor.setNacionalidade("Colombiana");
        autor.setDataNascimento(LocalDate.of(1927, 3, 6));

        repository.save(autor);
    }
}

