package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByNomeIgnoreCase(String nome);
}
