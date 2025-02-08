package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuário", description = "Gerenciamento de Usuários")
public class UsuarioController extends BaseController<UsuarioDTO> {

    private final UsuarioService usuarioService;

    @Autowired
    protected UsuarioController(UsuarioService service) {
        super(service);
        this.usuarioService = service;
    }
}
