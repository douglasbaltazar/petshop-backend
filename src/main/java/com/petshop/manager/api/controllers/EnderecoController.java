package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.EnderecoDTO;
import com.petshop.manager.domain.services.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereço", description = "Gerenciamento de Endereços")
public class EnderecoController extends BaseController<EnderecoDTO> {

    private final EnderecoService enderecoService;

    @Autowired
    protected EnderecoController(EnderecoService service) {
        super(service);
        this.enderecoService = service;
    }
}
