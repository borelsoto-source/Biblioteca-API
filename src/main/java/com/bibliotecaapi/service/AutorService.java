package com.bibliotecaapi.service;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        autorRepository.save(autor);
        return autor;
    }

    public List<Autor> buscarTodos(){
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id){
        return autorRepository.findById(id).orElse(null);
    }

    public List<Autor> buscarPorNome(String nome){
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Autor deletarPorId(Long id){
        Optional<Autor> autor_encontrado = autorRepository.findById(id);
        if(autor_encontrado.isPresent()){
            autorRepository.delete(autor_encontrado.get());
        }else{
            throw new RuntimeException ("Id nao encontrado");
        }
        return autor_encontrado.get();
    }

    public Autor atualizar(Long id, Autor autor){
        Autor entity = autorRepository.getReferenceById(id);
        atualizarDados(entity,autor);
        return autorRepository.save(entity);
    }

    public void atualizarDados(Autor autor, Autor obj){
        autor.setNome(obj.getNome());
        autor.setNacionalidade(obj.getNacionalidade());
        autor.setDataNascimento(obj.getDataNascimento());
        autor.setLivros(obj.getLivros());
    }
}
