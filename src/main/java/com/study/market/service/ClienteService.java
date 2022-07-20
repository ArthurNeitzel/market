package com.study.market.service;

import com.study.market.controller.DTO.ClienteDTO;
import com.study.market.entity.Cliente;
import com.study.market.exception.ResourceNotFoundException;
import com.study.market.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado.")
        );
    }

    public Cliente createCliente(ClienteDTO clienteDTO) {
        return clienteRepository.save(clienteDTO.toCliente());
    }

    public Cliente updateCliente(Long id, ClienteDTO clienteDTO){

        Cliente cliente = getCliente(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){
        Cliente cliente = getCliente(id);

        clienteRepository.delete(cliente);
    }
}
