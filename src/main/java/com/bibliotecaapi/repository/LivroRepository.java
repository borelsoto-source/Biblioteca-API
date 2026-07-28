package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorId(Long Id);
    List<Livro> findByAutorNomeContainingIgnoreCase(String nome);
    List<Livro> findByGenerosNomeIgnoreCase(String nome);
}
