package com.bibliotecaapi.service;

import com.bibliotecaapi.model.PerfilUsuario;
import com.bibliotecaapi.model.Usuario;
import com.bibliotecaapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setDataCadastro(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario nao encontrado para o id " + id));
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<Usuario> buscarPorPerfil(PerfilUsuario perfil) {
        return usuarioRepository.findByPerfil(perfil);
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Long id, Usuario usuario){
        Usuario usuarioEncontrado = buscarPorId(id);
        atualizarDados(usuarioEncontrado, usuario);
        return usuarioRepository.save(usuarioEncontrado);
    }

    public void atualizarDados(Usuario banco, Usuario usuarioAtualizado){
        banco.setNome(usuarioAtualizado.getNome());
        banco.setEmail(usuarioAtualizado.getEmail());
        banco.setTelefone(usuarioAtualizado.getTelefone());
        banco.setPerfil(usuarioAtualizado.getPerfil());
    }

    public void removerPorId(Long id){
        Usuario usuarioEncontrado = buscarPorId(id);
        usuarioRepository.deleteById(id);
    }

}
