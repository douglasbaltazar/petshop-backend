package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna uma lista.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<PetDTO>> listar() {
        Usuario user = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        List<PetDTO> lista = petService.listar();
        return ResponseEntity.ok(lista);
    }

}
