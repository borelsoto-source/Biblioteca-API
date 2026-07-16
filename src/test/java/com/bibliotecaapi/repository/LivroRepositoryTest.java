package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.model.GeneroLivro;
import com.bibliotecaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    void salvarLivro(){
        Livro livro = new Livro();
        livro.setTitulo("O  Em Tempos De Cólera");
        livro.setIsbn("784-8501552093");
        livro.setDataLancamento(LocalDate.of(1985,7,8));
        livro.setGenero(GeneroLivro.ROMANCE);

        Autor autor = autorRepository.findById(1L).orElse(null);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void deletarPorId(){
        livroRepository.deleteById(1L);
    }

}

