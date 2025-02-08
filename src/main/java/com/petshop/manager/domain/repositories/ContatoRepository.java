package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Contato;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends BaseRepository<Contato, Long> {
}
