package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Cliente;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

}
