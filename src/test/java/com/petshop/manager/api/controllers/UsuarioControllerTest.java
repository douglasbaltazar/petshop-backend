package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.UsuarioControllerTestConfig;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.UsuarioService;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@Import(UsuarioControllerTestConfig.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioService usuarioService;

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
    public void testListarUsuarios() throws Exception {
        // Cria um UsuarioDTO de exemplo
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setCpf("12345678900");
        usuarioDto.setNome("Usuário Teste");
        usuarioDto.setPerfil("Admin");
        usuarioDto.setSenha("senha123");

        List<UsuarioDTO> lista = Collections.singletonList(usuarioDto);

        when(usuarioService.listar()).thenReturn(lista);

        mockMvc.perform(get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cpf").value("12345678900"))
                .andExpect(jsonPath("$[0].nome").value("Usuário Teste"))
                .andExpect(jsonPath("$[0].perfil").value("Admin"));
    }

    @Test
    public void testGravarUsuario() throws Exception {
        // Cria um UsuarioDTO de entrada (sem id)
        UsuarioDTO inputDto = new UsuarioDTO();
        inputDto.setCpf("12345678900");
        inputDto.setNome("Novo Usuário");
        inputDto.setPerfil("Cliente");
        inputDto.setSenha("novaSenha");

        // Simula a resposta do serviço com um UsuarioDTO com id atribuído
        UsuarioDTO outputDto = new UsuarioDTO();
        outputDto.setId(1L);
        outputDto.setCpf("12345678900");
        outputDto.setNome("Novo Usuário");
        outputDto.setPerfil("Cliente");
        outputDto.setSenha("novaSenha");

        when(usuarioService.gravar(any(UsuarioDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/usuarios").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("Novo Usuário"))
                .andExpect(jsonPath("$.perfil").value("Cliente"));
    }

    @Test
    public void testAtualizarUsuario() throws Exception {
        // Cria um UsuarioDTO para atualizar
        UsuarioDTO inputDto = new UsuarioDTO();
        inputDto.setCpf("12345678900");
        inputDto.setNome("Usuário Atualizado");
        inputDto.setPerfil("Admin");
        inputDto.setSenha("senhaAtualizada");

        // Simula a resposta do serviço para atualização
        UsuarioDTO outputDto = new UsuarioDTO();
        outputDto.setId(1L);
        outputDto.setCpf("12345678900");
        outputDto.setNome("Usuário Atualizado");
        outputDto.setPerfil("Admin");
        outputDto.setSenha("senhaAtualizada");

        when(usuarioService.atualizar(any(UsuarioDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/usuarios/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("Usuário Atualizado"))
                .andExpect(jsonPath("$.perfil").value("Admin"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        // Para o DELETE, assumimos que o serviço apaga o usuário sem erros.
        mockMvc.perform(delete("/usuarios/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        // Simula a resposta do serviço para getById
        UsuarioDTO outputDto = new UsuarioDTO();
        outputDto.setId(1L);
        outputDto.setCpf("12345678900");
        outputDto.setNome("Usuário Existente");
        outputDto.setPerfil("Cliente");
        outputDto.setSenha("senhaExistente");

        when(usuarioService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("Usuário Existente"))
                .andExpect(jsonPath("$.perfil").value("Cliente"));
    }
}
