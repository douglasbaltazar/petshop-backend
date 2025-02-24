package com.petshop.manager.domain.repositories;

import com.petshop.manager.domain.model.Cliente;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {
    List<Cliente> findByUsuario(Usuario user);

}
