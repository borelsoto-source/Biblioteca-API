package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
