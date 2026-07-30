package com.bibliotecaapi.controller;

import com.bibliotecaapi.model.Emprestimo;
import com.bibliotecaapi.model.StatusEmprestimo;
import com.bibliotecaapi.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(emprestimoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> buscar(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) String titulo,
                                                   @RequestParam(required = false) StatusEmprestimo status){
        if(id != null){
            return ResponseEntity.ok().body(emprestimoService.buscarPorIdUsuario(id));
        }
        if(titulo != null){
            return ResponseEntity.ok().body(emprestimoService.buscarPorTituloLivro(titulo));
        }
        if(status != null){
            return ResponseEntity.ok().body(emprestimoService.buscarPorStatus(status));
        }

        return ResponseEntity.ok().body(emprestimoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Emprestimo> salvar(@RequestBody Emprestimo emprestimo){
        Emprestimo emprestimoSalvo = emprestimoService.salvar(emprestimo);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoSalvo);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long id){
        Emprestimo emprestimo =  emprestimoService.devolver(id);
        return ResponseEntity.ok().body(emprestimo);
    }

}
