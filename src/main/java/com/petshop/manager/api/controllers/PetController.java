package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.domain.services.PetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
@Tag(name = "Pet", description = "Gerenciamento de Pets")
public class PetController extends BaseController<PetDTO> {

    private final PetService petService;

    @Autowired
    protected PetController(PetService service) {
        super(service);
        this.petService = service;
    }
}
