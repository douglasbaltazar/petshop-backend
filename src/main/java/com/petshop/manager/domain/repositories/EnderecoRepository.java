package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Endereco;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends BaseRepository<Endereco, Long> {
}
