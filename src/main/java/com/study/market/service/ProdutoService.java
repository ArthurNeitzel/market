package com.study.market.service;

import com.study.market.controller.DTO.ProdutoDTO;
import com.study.market.entity.Produto;
import com.study.market.exception.ResourceNotFoundException;
import com.study.market.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto não encontrado.")
        );
    }

    public Produto createProduto(ProdutoDTO produtoDTO) {
        return produtoRepository.save(produtoDTO.toproduto());
    }

    public Produto updateProduto(Long id, ProdutoDTO produtoDTO){

        Produto produto = getProduto(id);

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());

        return produtoRepository.save(produto);
    }

    public void delete(Long id){
        Produto produto = getProduto(id);

        produtoRepository.delete(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
}
