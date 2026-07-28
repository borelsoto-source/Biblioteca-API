package com.bibliotecaapi.service;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public List<Autor> buscarTodos(){
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id){
       return autorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Autor nao encontrado com o id: " + id));
    }

    public List<Autor> buscarPorNome(String nome){
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public void deletarPorId(Long id){
        Autor autorEncontrado = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor nao encontrado com o id: " + id));

            validarExclusao(autorEncontrado);
            autorRepository.delete(autorEncontrado);
    }

    public void validarExclusao(Autor autor){
        if(!autor.getLivros().isEmpty()){
            throw new RuntimeException("Autor não pode ser excluído pois possui livros cadastrados");
        }
    }

    public Autor atualizar(Long id, Autor autor){
        Autor entity = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor nao encontrado com o id: " + id));

            atualizarDados(entity,autor);
            return autorRepository.save(entity);
    }

    public void atualizarDados(Autor autor, Autor autorAtualizado){
        autor.setNome(autorAtualizado.getNome());
        autor.setNacionalidade(autorAtualizado.getNacionalidade());
        autor.setDataNascimento(autorAtualizado.getDataNascimento());
    }
}
