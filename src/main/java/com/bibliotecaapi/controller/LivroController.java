package com.bibliotecaapi.controller;

import com.bibliotecaapi.model.Livro;
import com.bibliotecaapi.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    public LivroController(LivroService livroService)
        {this.livroService = livroService;}

    @GetMapping
    public ResponseEntity<List<Livro>> buscar(@RequestParam(required = false) String titulo,
                                              @RequestParam(required = false) Long autorId,
                                              @RequestParam(required = false) String autor,
                                              @RequestParam(required = false) String genero) {
        if (titulo != null) {
            return ResponseEntity.ok().body(livroService.buscarPorTitulo(titulo));
        }
        if (autorId != null) {
            return ResponseEntity.ok().body(livroService.buscarPorIdDoAutor(autorId));
        }
        if (autor != null) {
            return ResponseEntity.ok().body(livroService.buscarPorNomeDoAutor(autor));
        }
        if (genero != null) {
            return ResponseEntity.ok().body(livroService.buscarPorGenero(genero));
        }

        return ResponseEntity.ok().body(livroService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(livroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro){
        Livro livroSalvo = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        livro = livroService.atualizar(id, livro);
        return ResponseEntity.ok().body(livro);
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id){
       livroService.inativar(id);
       return ResponseEntity.noContent().build();
    }
}
