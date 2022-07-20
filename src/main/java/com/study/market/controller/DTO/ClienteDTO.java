package com.study.market.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.market.entity.Cliente;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ClienteDTO {
    private String nome;
    private String cpf;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataNascimento;

    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setDataNascimento(this.dataNascimento);

        return cliente;
    }
}
