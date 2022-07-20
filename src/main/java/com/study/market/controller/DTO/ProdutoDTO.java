package com.study.market.controller.DTO;

import com.study.market.entity.Produto;
import lombok.Data;
@Data
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public Produto toproduto() {
        Produto result = new Produto();
        result.setNome(this.nome);
        result.setDescricao(this.descricao);
        result.setPreco(this.preco);
        result.setQuantidade(this.quantidade);

        return result;
    }
}
