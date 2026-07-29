package com.bibliotecaapi.controller;

import com.bibliotecaapi.model.PerfilUsuario;
import com.bibliotecaapi.model.Usuario;
import com.bibliotecaapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscar(@RequestParam(required = false) String nome,
                                                @RequestParam(required = false) String email,
                                                @RequestParam(required = false) PerfilUsuario perfil){

        if(nome != null){
            return ResponseEntity.ok(usuarioService.buscarPorNome(nome));
        }
        if(email != null){
            return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
        }
        if(perfil != null){
            return ResponseEntity.ok(usuarioService.buscarPorPerfil(perfil));
        }

        return ResponseEntity.ok(usuarioService.buscarTodos());

    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        usuario = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        usuario = usuarioService.atualizar(id, usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarPorId(@PathVariable Long id){
        usuarioService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }
}
