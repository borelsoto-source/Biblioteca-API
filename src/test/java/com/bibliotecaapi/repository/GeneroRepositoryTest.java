package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Genero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeneroRepositoryTest {

    @Autowired
    private GeneroRepository repository;

    @Test
    void salvarGenero(){
        Genero genero = new Genero();
        genero.setNome("COMEDIA CRONICA");
        genero.setDescricao("Foca em mal-entendidos e desventuras do dia a dia.");

        repository.save(genero);
    }
}

