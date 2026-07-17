package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.model.Genero;
import com.bibliotecaapi.model.Livro;
import com.bibliotecaapi.model.StatusLivro;
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
    private GeneroRepository generoRepository;

    @Test
    void salvarLivro(){
        Livro livro = new Livro();
        livro.setTitulo("O Amor Em Tempos De Cólera");
        livro.setIsbn("784-8501552093");
        livro.setDataLancamento(LocalDate.of(2001,5,22));

        Autor autor = autorRepository.findById(2L).orElse(null);
        livro.setAutor(autor);

        Set<Genero> genero = new HashSet<>();
        genero.add(generoRepository.findById(1L).orElse(null));

        livro.setGeneros(genero);

        livroRepository.save(livro);
    }

    @Test
    void deletarPorId(){

        livroRepository.deleteById(3L);
    }

    @Test
    void atualizarLivro(){
        Livro livro = livroRepository.findById(2L).orElse(null);
        livro.setStatus(StatusLivro.EMPRESTADO);
        livroRepository.save(livro);
    }
}

