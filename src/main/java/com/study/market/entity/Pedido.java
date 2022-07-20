package com.study.market.entity;

import com.study.market.exception.ResourceNotFoundException;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "PEDIDO")
@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "total")
    private Double total;

    @Column(name = "data")
    private Date dataCompra;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PRODUTO_PEDIDO",
            joinColumns = @JoinColumn(name = "id_pedido", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "id_produto", referencedColumnName = "ID"))
    private List<Produto> produtos = new ArrayList<>();

    public void addProduto(Produto produto){
        this.produtos.add(produto);
        this.total += produto.getPreco() * produto.getQuantidade();
    }

    public void removeProduto(Long idProduto){
        Produto produto = this.produtos.stream()
            .filter(prod -> Objects.equals(prod.getId(), idProduto))
            .findFirst().orElseThrow(
                () -> new ResourceNotFoundException("Produto não disponível no pedido.")
            );
        produtos.remove(produto);
        this.total -= produto.getPreco() * produto.getQuantidade();
    }
}
