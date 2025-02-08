package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Atendimento;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends BaseRepository<Atendimento, Long> {
}
