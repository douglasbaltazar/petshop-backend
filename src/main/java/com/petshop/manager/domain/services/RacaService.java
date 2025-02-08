package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.RacaDTO;
import com.petshop.manager.domain.model.Raca;
import com.petshop.manager.domain.repositories.RacaRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class RacaService extends BaseService<Raca, RacaDTO> {
    @Autowired
    private RacaRepository racaRepository;

    @Autowired
    public RacaService(RacaRepository repository) {
        super(repository, RacaDTO.class, Raca.class);
        this.racaRepository = repository;
    }
}
