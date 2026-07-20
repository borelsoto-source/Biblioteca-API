package com.bibliotecaapi.controller;

import com.bibliotecaapi.model.Autor;
import com.bibliotecaapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Autor> cadastrar(@RequestBody Autor autor){
        Autor autorSalvo = autorService.salvar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> buscar(@RequestParam(required = false) String nome) {
        if (nome != null) {
            return ResponseEntity.ok().body(autorService.buscarPorNome(nome));
        }
        return ResponseEntity.ok().body(autorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id){
       Autor autor = autorService.buscarPorId(id);
       return ResponseEntity.ok().body(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable ("id") Long id){
        autorService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor autor){
        autor = autorService.atualizar(id, autor);
        return ResponseEntity.ok().body(autor);
    }
}
