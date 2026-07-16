package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.model.GeneroLivro;
import com.bibliotecaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GeneroLivroRepository generoRepository;

    @Test
    void salvarLivro(){
        Livro livro = new Livro();
        livro.setTitulo("Dias Perfeitos");
        livro.setIsbn("773-2487155895");
        livro.setDataLancamento(LocalDate.of(2014,2,17));

        Autor autor = autorRepository.findById(2L).orElse(null);
        livro.setAutor(autor);

        Set<GeneroLivro> generos = new HashSet<>();
        generos.add(generoRepository.findById(1L).orElse(null));
        generos.add(generoRepository.findById(3L).orElse(null));

        livro.setGenero(generos);

        livroRepository.save(livro);
    }

    @Test
    void deletarPorId(){
        livroRepository.deleteById(3L);
    }

}

