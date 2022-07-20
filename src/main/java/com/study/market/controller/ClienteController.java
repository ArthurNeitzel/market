package com.study.market.controller;

import com.study.market.controller.DTO.ClienteDTO;
import com.study.market.entity.Cliente;
import com.study.market.service.ClienteService;
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
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO clienteDTO) throws URISyntaxException {
        Cliente response = clienteService.createCliente(clienteDTO);
        return ResponseEntity.created(new URI("/v1/clientes/" + response.getId())).body(response);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Cliente>> listCliente(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.getCliente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        Cliente response = clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
