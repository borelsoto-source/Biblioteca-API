package com.bibliotecaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name= "tb_genero_livro")
@Data
public class GeneroLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "genero")
    private List<Livro> livro;
}
