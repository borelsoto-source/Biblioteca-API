package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.Emprestimo;
import com.bibliotecaapi.model.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuarioId(Long id);
    List<Emprestimo> findByLivroTituloContainingIgnoreCase(String titulo);
    List<Emprestimo> findByStatus(StatusEmprestimo status);
}
