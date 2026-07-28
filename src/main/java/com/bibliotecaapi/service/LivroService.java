package com.bibliotecaapi.service;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.model.Genero;
import com.bibliotecaapi.model.Livro;
import com.bibliotecaapi.model.StatusLivro;
import com.bibliotecaapi.repository.GeneroRepository;
import com.bibliotecaapi.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorService autorService;
    private final GeneroRepository generoRepository;

    public LivroService(LivroRepository livroRepository
            ,AutorService autorService, GeneroRepository generoRepository) {
        this.livroRepository = livroRepository;
        this.autorService = autorService;
        this.generoRepository = generoRepository;
    }

    public Livro salvar(Livro livro) {
       Autor autorEncontrado = autorService.buscarPorId(livro.getAutor().getId());
       livro.setAutor(autorEncontrado);

        Set<Genero> generos = buscarGenerosPorId(livro.getGeneros());
        livro.setGeneros(generos);

        livro.setStatus(StatusLivro.DISPONIVEL);

        return  livroRepository.save(livro);
    }

    public Livro buscarPorId(Long id){
        return livroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Nenhum livro encontrado para o id "+id));
    }

    public List<Livro> buscarTodos(){
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorTitulo(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorIdDoAutor(Long id){
        autorService.buscarPorId(id);
        return livroRepository.findByAutorId(id);
    }

    public List<Livro> buscarPorNomeDoAutor(String nome){
        autorService.buscarPorNome(nome);
        return livroRepository.findByAutorNomeContainingIgnoreCase(nome);
    }

    public List<Livro> buscarPorGenero(String nome) {
        buscarGenerosPorNome(nome);
        List<Livro> livrosEncontrados = livroRepository.findByGenerosNomeIgnoreCase(nome);

        if(livrosEncontrados.isEmpty()){
            throw new RuntimeException("Nenhum livro encontrado para o genero "+nome);
        }
        return livrosEncontrados;

    }

    private Set<Genero> buscarGenerosPorId(Set<Genero> generosRecebidos){

        Set<Genero> generosEncontrados = new HashSet<>();

        for(Genero genero : generosRecebidos) {
            Genero generoEncontrado = generoRepository.findById(genero.getId())
                    .orElseThrow(()-> new RuntimeException("Genero inexistente"));
            generosEncontrados.add(generoEncontrado);
        }
        return generosEncontrados;
    }

    private Genero buscarGenerosPorNome(String nome){
        Genero generoEncontrado = generoRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(()-> new RuntimeException("Nenhum genero encontrado para "+nome));
        return generoEncontrado;
    }


    public Livro atualizar(Long id, Livro livro) {
        Livro entity = livroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Livro nao encontrado para o id "+id));

        Autor autorEncontrado = autorService.buscarPorId(livro.getAutor().getId());
        livro.setAutor(autorEncontrado);

        Set<Genero> generoEcontrado = buscarGenerosPorId(livro.getGeneros());
        livro.setGeneros(generoEcontrado);

        atualizarDados(entity, livro);
        return livroRepository.save(entity);
    }

    public void atualizarDados(Livro banco, Livro livroAtualizado) {
        banco.setTitulo(livroAtualizado.getTitulo());
        banco.setIsbn(livroAtualizado.getIsbn());
        banco.setDataLancamento(livroAtualizado.getDataLancamento());
        banco.setGeneros(livroAtualizado.getGeneros());
        banco.setAutor(livroAtualizado.getAutor());
    }

    public void inativar(Long id){
        Livro livroEncontrado = livroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Livro nao encontrado para o id "+id));

        validarInativacao(livroEncontrado);
        livroEncontrado.setStatus(StatusLivro.INATIVO);
        livroRepository.save(livroEncontrado);
    }

    public void validarInativacao(Livro livro){
        if(livro.getStatus().equals(StatusLivro.EMPRESTADO)){
            throw new RuntimeException("Livro nao pode ser inativado pois seu status é EMPRESTADO");
        }
    }
}
