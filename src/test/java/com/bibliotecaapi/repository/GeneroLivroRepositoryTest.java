package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.model.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class GeneroLivroRepositoryTest {

    @Autowired
    private GeneroLivroRepository repository;

    @Test
    void salvarGenero(){
        GeneroLivro genero = new GeneroLivro();
        genero.setNome("SUSPENSE");
        genero.setDescricao("Envolve investigações, crimes e mistérios.");

        repository.save(genero);
    }
}

