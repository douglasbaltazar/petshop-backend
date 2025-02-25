package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.RacaControllerTestConfig;
import com.petshop.manager.data.dto.RacaDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.RacaService;
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

@WebMvcTest(RacaController.class)
@Import(RacaControllerTestConfig.class)
public class RacaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RacaService racaService;

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
    public void testListarRacas() throws Exception {
        // Cria um RacaDTO de exemplo
        RacaDTO racaDto = new RacaDTO();
        racaDto.setId(1L);
        racaDto.setDescricao("Raça Teste");
        // Adicione outros atributos se necessário

        List<RacaDTO> lista = Collections.singletonList(racaDto);

        // Configura o comportamento do mock para o método listar()
        when(racaService.listar()).thenReturn(lista);

        // Realiza a chamada GET e verifica o resultado
        mockMvc.perform(get("/racas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Raça Teste"));
    }

    @Test
    public void testGravarRaca() throws Exception {
        // Cria um RacaDTO de entrada (sem id)
        RacaDTO inputDto = new RacaDTO();
        inputDto.setDescricao("Nova Raça");

        // Simula a resposta do serviço com um RacaDTO com id atribuído
        RacaDTO outputDto = new RacaDTO();
        outputDto.setId(1L);
        outputDto.setDescricao("Nova Raça");

        when(racaService.gravar(any(RacaDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/racas").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Nova Raça"));
    }

    @Test
    public void testAtualizarRaca() throws Exception {
        // Cria um RacaDTO para atualizar
        RacaDTO inputDto = new RacaDTO();
        inputDto.setDescricao("Raça Atualizada");

        // Simula a resposta do serviço para atualização
        RacaDTO outputDto = new RacaDTO();
        outputDto.setId(1L);
        outputDto.setDescricao("Raça Atualizada");

        when(racaService.atualizar(any(RacaDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/racas/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Raça Atualizada"));
    }

    @Test
    public void testDeleteRaca() throws Exception {
        // Para o DELETE, assumimos que o serviço apaga a raça sem erros.
        mockMvc.perform(delete("/racas/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRacaById() throws Exception {
        // Simula a resposta do serviço para getById
        RacaDTO outputDto = new RacaDTO();
        outputDto.setId(1L);
        outputDto.setDescricao("Raça Existente");

        when(racaService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/racas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Raça Existente"));
    }
}
