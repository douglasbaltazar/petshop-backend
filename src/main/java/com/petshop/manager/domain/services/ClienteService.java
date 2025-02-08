package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.domain.model.Cliente;
import com.petshop.manager.domain.repositories.AtendimentoRepository;
import com.petshop.manager.domain.repositories.ClienteRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseService<Cliente, ClienteDTO> {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        super(repository, ClienteDTO.class, Cliente.class);
        this.clienteRepository = repository;
    }
}
