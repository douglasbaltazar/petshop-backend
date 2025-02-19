package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.EnderecoDTO;
import com.petshop.manager.domain.model.Endereco;
import com.petshop.manager.domain.repositories.EnderecoRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends BaseService<Endereco, EnderecoDTO> {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    public EnderecoService(EnderecoRepository repository) {
        super(repository, EnderecoDTO.class, Endereco.class);
        this.enderecoRepository = repository;
    }

    public EnderecoDTO gravar(EnderecoDTO enderecoDTO, Long idCliente) {
        ClienteDTO clienteDTO = clienteService.getDto(idCliente);
        enderecoDTO.setCliente(clienteDTO);
        return super.gravar(enderecoDTO);
    }
}
