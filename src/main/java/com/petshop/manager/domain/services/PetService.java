package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.domain.model.Pet;
import com.petshop.manager.domain.repositories.PetRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService extends BaseService<Pet, PetDTO> {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    public PetService(PetRepository repository) {
        super(repository, PetDTO.class, Pet.class);
        this.petRepository = repository;
    }
}
