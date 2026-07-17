package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class EmprestimoRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(AutorRepositoryTest.class);

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Test
    void fazerEmprestimo(){
        Emprestimo emprestimo = new Emprestimo();

        Usuario usuario = usuarioRepository.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));

        Livro livro = livroRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Livro nao encontrado"));

        if(livro.getStatus() == StatusLivro.INATIVO
                || livro.getStatus() == StatusLivro.EMPRESTADO){
            throw new IllegalStateException(
                    "Livro indisponível: " + livro.getStatus());
        }else{
            emprestimo.setLivro(livro);
        }

        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevista(emprestimo.getDataEmprestimo().plusDays(15));
        emprestimo.setStatus(StatusEmprestimo.EM_ANDAMENTO);

        repository.save(emprestimo);
    }

    @Test
    void atualizaStatusEmprestimo(){
        Emprestimo emprestimo = repository.findById(1L).orElse(null);
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(10));
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        repository.save(emprestimo);
    }
}
