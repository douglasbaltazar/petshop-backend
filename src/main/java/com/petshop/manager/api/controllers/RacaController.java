package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.RacaDTO;
import com.petshop.manager.domain.services.RacaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/racas")
@Tag(name = "Raça", description = "Gerenciamento de Raças")
public class RacaController extends BaseController<RacaDTO> {

    private final RacaService racaService;

    @Autowired
    protected RacaController(RacaService service) {
        super(service);
        this.racaService = service;
    }
}
