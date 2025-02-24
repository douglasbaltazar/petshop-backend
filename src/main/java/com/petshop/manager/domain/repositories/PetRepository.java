package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Pet;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends BaseRepository<Pet, Long> {
    List<Pet> findByCriadoPor_IdOrCliente_Id(Long usuarioId, Long clienteId);
}
