package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.PerfilUsuario;
import com.bibliotecaapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    List<Usuario> findByEmailContainingIgnoreCase(String email);
    List<Usuario> findByPerfil(PerfilUsuario perfil);
}
