package com.petshop.manager.api.controllers.base;

import com.petshop.manager.data.dto.base.BaseEntityDTO;
import com.petshop.manager.data.dto.base.ResponseDTO;
import com.petshop.manager.domain.services.base.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @param <D> Tipo DTO
 */
public abstract class BaseController<D extends BaseEntityDTO>  {
    private final BaseService<?, D> service;

    protected BaseController(BaseService<?, D> service) {
        this.service = service;
    }
    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna uma lista.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<D>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping()
    @Operation(summary = "Cadastra um novo", description = "Adiciona um novo à base de dados.")
    @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<D> gravar(@Validated @RequestBody D dto) {
        return ResponseEntity.ok(service.gravar(dto));
    }

    @GetMapping(path = "paginado")
    @Operation(summary = "Listar paginado", description = "Retorna uma lista paginada.")
    @ApiResponse(responseCode = "200", description = "Lista paginada retornada com sucesso")
    public Page<? extends BaseEntityDTO> paginado(Pageable pageable) {
        return service.paginado(pageable);
    }

    @PutMapping()
    @Operation(summary = "Atualizar um registro", description = "Atualiza um registro na base.")
    @ApiResponse(responseCode = "201", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<D> atualizar(@Validated @RequestBody D dto) {
        return ResponseEntity.ok(service.atualizar(dto));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Exclui um registro", description = "Exclui um registro na base.")
    @ApiResponse(responseCode = "201", description = "Excluido com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id) {
        service.apagarPorId(id);
        return ResponseEntity.ok(new ResponseDTO("Registro excluído com sucesso."));
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Recupera um registro por id", description = "Recupera um registro com o id.")
    @ApiResponse(responseCode = "201", description = "Recuperado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<D> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getDto(id));
    }
}
