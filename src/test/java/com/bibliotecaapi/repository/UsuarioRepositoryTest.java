package com.bibliotecaapi.repository;

import com.bibliotecaapi.model.PerfilUsuario;
import com.bibliotecaapi.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void inserirUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setTelefone("+5511737222293");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("654321");
        usuario.setPerfil(PerfilUsuario.LEITOR);
        usuario.setDataCadastro(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        repository.save(usuario);
    }

    @Test
    public void deletarUsuario() {
        repository.deleteById(1L);
    }
}
