package com.bibliotecaapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "tb_genero_livro")

public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

//    @ToString.Exclude
    @ManyToMany(mappedBy = "generos")
    private Set<Livro> livro = new HashSet<>();
}
