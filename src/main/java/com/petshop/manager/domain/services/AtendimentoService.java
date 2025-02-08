package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.AtendimentoDTO;
import com.petshop.manager.domain.model.Atendimento;
import com.petshop.manager.domain.repositories.AtendimentoRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class AtendimentoService extends BaseService<Atendimento, AtendimentoDTO> {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    public AtendimentoService(AtendimentoRepository repository) {
        super(repository, AtendimentoDTO.class, Atendimento.class);
        this.atendimentoRepository = repository;
    }
}
