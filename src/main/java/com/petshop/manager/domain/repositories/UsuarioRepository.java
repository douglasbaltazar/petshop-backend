package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
}
