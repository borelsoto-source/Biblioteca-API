package com.bibliotecaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_livro")
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @ManyToMany
    @JoinTable(name= "genero_livro",
            joinColumns = @JoinColumn(name= "id_livro"),
            inverseJoinColumns = @JoinColumn(name= "id_genero"))
    private Set<GeneroLivro> genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;
}
