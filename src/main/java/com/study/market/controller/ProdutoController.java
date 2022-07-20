package com.study.market.controller;

import com.study.market.controller.DTO.ProdutoDTO;
import com.study.market.entity.Produto;
import com.study.market.service.ProdutoService;
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
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoDTO produtoDTO) throws URISyntaxException {
        Produto response = produtoService.createProduto(produtoDTO);
        return ResponseEntity.created(new URI("/v1/produtos/" + response.getId())).body(response);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Produto>> listProduto(){
        return ResponseEntity.ok(produtoService.findAll());
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.getProduto(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        Produto response = produtoService.updateProduto(id, produtoDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduto(@PathVariable Long id) {
        produtoService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
