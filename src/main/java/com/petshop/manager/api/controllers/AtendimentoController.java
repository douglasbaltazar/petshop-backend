package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.AtendimentoDTO;
import com.petshop.manager.domain.services.AtendimentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendimentos")
@Tag(name = "Atendimento", description = "Gerenciamento de Atendimentos")
public class AtendimentoController extends BaseController<AtendimentoDTO> {

    private final AtendimentoService atendimentoService;

    @Autowired
    protected AtendimentoController(AtendimentoService service) {
        super(service);
        this.atendimentoService = service;
    }
}
