package com.bibliotecaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @ManyToMany
    @JoinTable(
            name= "genero_livro",
            joinColumns = @JoinColumn(name= "id_livro"),
            inverseJoinColumns = @JoinColumn(name= "id_genero")
    )
    private Set<Genero> generos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos = new ArrayList<>();
}
