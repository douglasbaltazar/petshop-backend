package com.petshop.manager.api.controllers;

import com.petshop.manager.api.controllers.base.BaseController;
import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.services.ClienteService;
import com.petshop.manager.domain.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
public class ClienteController extends BaseController<ClienteDTO> {

    private final ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected ClienteController(ClienteService service) {
        super(service);
        this.clienteService = service;
    }

    @Override
    public ResponseEntity<ClienteDTO> gravar(ClienteDTO dto) {
        UsuarioDTO usuarioDTO;
        if (dto.getUsuario() != null) {
            if(dto.getUsuario().getSenha() != null) {
                dto.getUsuario().setSenha(passwordEncoder.encode(dto.getUsuario().getSenha()));
            }
            usuarioDTO = usuarioService.gravar(dto.getUsuario());

            dto.setUsuario(usuarioDTO);
        }
        return super.gravar(dto);
    }
}
