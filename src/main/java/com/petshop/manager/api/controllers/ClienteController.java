package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.domain.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
public class ClienteController extends BaseController<ClienteDTO> {

    private final ClienteService clienteService;

    @Autowired
    protected ClienteController(ClienteService service) {
        super(service);
        this.clienteService = service;
    }
}
