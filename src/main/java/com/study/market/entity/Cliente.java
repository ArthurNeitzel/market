package com.study.market.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "CLIENTE")
@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 250)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

}
