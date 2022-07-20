package com.study.market.service;

import com.study.market.controller.DTO.PedidoDTO;
import com.study.market.controller.DTO.ProdutoDTO;
import com.study.market.entity.Cliente;
import com.study.market.entity.Pedido;
import com.study.market.entity.Produto;
import com.study.market.exception.NotPossibleException;
import com.study.market.exception.ResourceNotFoundException;
import com.study.market.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public Pedido getPedido(Long id) {
        return pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado.")
        );
    }

    public Pedido createPedido(PedidoDTO pedidoDTO) {
        return pedidoRepository.save(pedidoDTO.toPedido());
    }

    public Pedido updatePedido(Long id, PedidoDTO pedidoDTO){

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado.")
        );

        pedido.setDataCompra(pedidoDTO.getDataCompra());

        return pedidoRepository.save(pedido);
    }

    public void delete(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido não encontrado.")
        );

        pedidoRepository.delete(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public void addCliente(Long idPedido, Long idCliente){
        Cliente cliente = clienteService.getCliente(idCliente);

        Pedido pedido = getPedido(idPedido);

        if(pedido.getCliente() != null){
            throw new NotPossibleException("Pedido já possui cliente cadastrado.");
        }

        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);
    }

    public void removeCliente(Long idPedido){
        Pedido pedido = getPedido(idPedido);

        if(pedido.getCliente() == null){
            throw new NotPossibleException("Pedido não possui cliente cadastrado");
        }

        pedido.setCliente(null);
        pedidoRepository.save(pedido);
    }

    public void addProduto(Long idPedido, Long idProduto){
        Produto produto = produtoService.getProduto(idProduto);

        Pedido pedido = getPedido(idPedido);

        if(pedido.getProdutos().contains(produto)){
            throw new NotPossibleException("Pedido já contem esse produto.");
        }

        pedido.addProduto(produto);
        pedidoRepository.save(pedido);
    }

    public void removeProduto(Long idPedido, Long idProduto){
        Pedido pedido = getPedido(idPedido);
        pedido.removeProduto(idProduto);
        pedidoRepository.save(pedido);
    }
}
