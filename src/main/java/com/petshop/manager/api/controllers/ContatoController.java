package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.ContatoDTO;
import com.petshop.manager.domain.services.ContatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contatos")
@Tag(name = "Contato", description = "Gerenciamento de Contatos")
public class ContatoController extends BaseController<ContatoDTO> {

    private final ContatoService contatoService;

    @Autowired
    protected ContatoController(ContatoService service) {
        super(service);
        this.contatoService = service;
    }
}
