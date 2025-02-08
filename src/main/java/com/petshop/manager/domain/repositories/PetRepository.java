package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Pet;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends BaseRepository<Pet, Long> {
}
