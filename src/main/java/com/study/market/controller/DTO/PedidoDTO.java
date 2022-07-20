package com.study.market.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.market.entity.Pedido;
import lombok.Data;

import java.util.Date;

@Data
public class PedidoDTO {

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataCompra;

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setDataCompra(this.getDataCompra());
        pedido.setTotal(0.0);
        return pedido;
    }
}
