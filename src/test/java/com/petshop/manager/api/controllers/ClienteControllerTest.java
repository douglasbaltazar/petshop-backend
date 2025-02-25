package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.ClienteControllerTestConfig;
import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.ClienteRepository;
import com.petshop.manager.domain.repositories.UsuarioRepository;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import com.petshop.manager.domain.services.ClienteService;
import com.petshop.manager.domain.services.UsuarioService;
import com.petshop.manager.domain.utils.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(ClienteController.class)
@Import(ClienteControllerTestConfig.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setupSecurityContext() {
        Usuario user = new Usuario();
        user.setId(1L);
        user.setPerfil("Admin");
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testListarClientes() throws Exception {
        // Configura o comportamento do mock para o método listar()
        ClienteDTO dto = new ClienteDTO();
        UsuarioDTO userDto = new UsuarioDTO();
        userDto.setId(1L);
        userDto.setPerfil("Admin");
        userDto.setNome("Cliente Teste");
        dto.setId(1L);
        dto.setUsuario(userDto);
        List<ClienteDTO> lista = Collections.singletonList(dto);

        // Configura o mock injetado
        Mockito.when(clienteService.listar()).thenReturn(lista);

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].usuario.nome").value("Cliente Teste"));
    }

    @Test
    public void testGravarCliente() throws Exception {
        // Cria um ClienteDTO de entrada (sem id)
        ClienteDTO inputDto = new ClienteDTO();
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Cliente Teste");
        usuarioDto.setPerfil("Admin");
        inputDto.setUsuario(usuarioDto);

        // Simula a resposta do serviço com um ClienteDTO que possui id atribuído
        ClienteDTO outputDto = new ClienteDTO();
        outputDto.setId(1L);
        usuarioDto.setNome("Cliente Teste");
        outputDto.setUsuario(usuarioDto);

        when(clienteService.gravar(any(ClienteDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/clientes").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario.id").value(1))
                .andExpect(jsonPath("$.usuario.nome").value("Cliente Teste"));
    }

    @Test
    public void testAtualizarCliente() throws Exception {
        // Cria um ClienteDTO para atualizar
        ClienteDTO inputDto = new ClienteDTO();
        // inputDto.setNome("Cliente Atualizado");
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Cliente Teste");
        usuarioDto.setPerfil("Admin");
        inputDto.setUsuario(usuarioDto);

        // Simula a resposta do serviço para atualização
        ClienteDTO outputDto = new ClienteDTO();
        outputDto.setId(1L);
        usuarioDto.setNome("Cliente Atualizado");
        outputDto.setUsuario(usuarioDto);

        when(clienteService.atualizar(any(ClienteDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/clientes/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario.id").value(1))
                .andExpect(jsonPath("$.usuario.nome").value("Cliente Atualizado"));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/clientes/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClienteById() throws Exception {
        // Simula a resposta do serviço para getById
        ClienteDTO outputDto = new ClienteDTO();
        outputDto.setId(1L);
        // outputDto.setNome("Cliente Existente");
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Cliente Teste");
        usuarioDto.setPerfil("Admin");
        outputDto.setUsuario(usuarioDto);

        when(clienteService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.usuario.nome").value("Cliente Teste"));
    }
}
