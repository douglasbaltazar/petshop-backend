package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.UsuarioRepository;
import com.petshop.manager.domain.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService extends BaseService<Usuario, UsuarioDTO> {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        super(repository, UsuarioDTO.class, Usuario.class);
        this.usuarioRepository = repository;
    }
}
