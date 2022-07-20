package com.study.market.controller;

import com.study.market.controller.DTO.PedidoDTO;
import com.study.market.entity.Pedido;
import com.study.market.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoDTO pedidoDTO) throws URISyntaxException {
        Pedido response = pedidoService.createPedido(pedidoDTO);
        return ResponseEntity.created(new URI("/v1/pedidos/" + response.getId())).body(response);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Pedido>> listPedido(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.getPedido(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO){
        Pedido response = pedidoService.updatePedido(id, pedidoDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{idPedido}/cliente/{idCliente}")
    public ResponseEntity<String> addCliente(@PathVariable Long idPedido, @PathVariable Long idCliente){
        pedidoService.addCliente(idPedido, idCliente);
        return ResponseEntity.ok("Cliente Adicionado com sucesso");
    }

        @PostMapping("/{idPedido}/produto/{idProduto}")
    public ResponseEntity<String> addProduto(@PathVariable Long idPedido, @PathVariable Long idProduto){
        pedidoService.addProduto(idPedido, idProduto);
        return ResponseEntity.ok("Produto Adicionado com sucesso");
    }

    @DeleteMapping("/{id}/cliente")
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        pedidoService.removeCliente(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/produto/{idProduto}")
    public ResponseEntity deleteProduto(@PathVariable Long id, @PathVariable Long idProduto) {
        pedidoService.removeProduto(id, idProduto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
