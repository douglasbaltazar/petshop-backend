package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Raca;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaRepository extends BaseRepository<Raca, Long> {
}
