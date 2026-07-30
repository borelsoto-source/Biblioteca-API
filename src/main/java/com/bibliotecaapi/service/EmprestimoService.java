package com.bibliotecaapi.service;

import com.bibliotecaapi.model.*;
import com.bibliotecaapi.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final UsuarioService usuarioService;
    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public Emprestimo buscarPorId(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Emprestimo nao encontrado para o id "+id));;
        verificarAtraso(emprestimo);
        return emprestimo;

    }

    public List<Emprestimo> buscarPorIdUsuario(Long id){
        usuarioService.buscarPorId(id);
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(id);
        verificarAtrasos(emprestimos);
        return emprestimos;
    }

    public List<Emprestimo> buscarPorTituloLivro(String titulo){
        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroTituloContainingIgnoreCase(titulo);
        verificarAtrasos(emprestimos);
        return emprestimos;
    }

    public List<Emprestimo> buscarPorStatus(StatusEmprestimo status){
        List<Emprestimo> emprestimos = emprestimoRepository.findByStatus(status);
        verificarAtrasos(emprestimos);
        return emprestimos;
    }

    public List<Emprestimo> buscarTodos(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        verificarAtrasos(emprestimos);
        return emprestimos;
    }

    @Transactional
    public Emprestimo salvar(Emprestimo emprestimo){
        Usuario usuario = usuarioService.buscarPorId(emprestimo.getUsuario().getId());
        emprestimo.setUsuario(usuario);

        Livro livro = livroService.buscarPorId(emprestimo.getLivro().getId());

        livroService.validarDisponibilidade(livro);
        emprestimo.setLivro(livro);
        livroService.atualizarStatus(livro.getId(), StatusLivro.EMPRESTADO);

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevista(emprestimo.getDataEmprestimo().plusDays(15));
        emprestimo.setStatus(StatusEmprestimo.EM_ANDAMENTO);
        return emprestimoRepository.save(emprestimo);

    }

    @Transactional
    public Emprestimo devolver (Long id){
        Emprestimo emprestimo = buscarPorId(id);
        Livro livro = emprestimo.getLivro();

        if(emprestimo.getStatus()==(StatusEmprestimo.EM_ANDAMENTO)){
            emprestimo.setDataDevolucao(LocalDate.now());
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
            livroService.atualizarStatus(livro.getId(), StatusLivro.DISPONIVEL);
        }else{
            throw new RuntimeException("Impossivel delvolver pois status do emprestimo é diferente de 'EM_ANDAMENTO'");
        }

        return emprestimoRepository.save(emprestimo);
    }

    private void verificarAtraso(Emprestimo emprestimo){
        if(emprestimo.getStatus() == (StatusEmprestimo.EM_ANDAMENTO)
                && emprestimo.getDataPrevista().isBefore(LocalDate.now())){
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            emprestimoRepository.save(emprestimo);
        }
    }

    private void verificarAtrasos(List<Emprestimo> emprestimos) {
        for(Emprestimo emprestimo : emprestimos) {
            verificarAtraso(emprestimo);
        }
    }

}
