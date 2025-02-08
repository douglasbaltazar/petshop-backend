package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.ContatoDTO;
import com.petshop.manager.domain.model.Contato;
import com.petshop.manager.domain.repositories.ContatoRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService extends BaseService<Contato, ContatoDTO> {
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    public ContatoService(ContatoRepository repository) {
        super(repository, ContatoDTO.class, Contato.class);
        this.contatoRepository = repository;
    }
}
