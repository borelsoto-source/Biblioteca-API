package com.bibliotecaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name= "tb_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name= "data_prevista")
    private LocalDate dataPrevista;

    @Column(name= "data_devolucao")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne
    @JoinColumn(name= "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name= "id_livro")
    private Livro livro;
}
