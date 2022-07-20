package com.study.market.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "PRODUTO")
@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 250)
    private String nome;

    @Column(name = "descricao", length = 250)
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade")
    private Integer quantidade;
}
