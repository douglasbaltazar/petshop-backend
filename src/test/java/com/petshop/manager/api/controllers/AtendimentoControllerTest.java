package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.AtendimentoControllerTestConfig;
import com.petshop.manager.data.dto.AtendimentoDTO;
import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.AtendimentoService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

@WebMvcTest(AtendimentoController.class)
@Import(AtendimentoControllerTestConfig.class)
public class AtendimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AtendimentoService atendimentoService;

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
    public void testListarAtendimentos() throws Exception {
        AtendimentoDTO dto = new AtendimentoDTO();
        dto.setId(1L);
        dto.setDescricao("Atendimento Teste");
        dto.setValor(BigDecimal.valueOf(100.50));
        dto.setData(LocalDateTime.now());

        PetDTO petDto = new PetDTO();
        petDto.setId(1L);
        petDto.setNome("Pet Teste");
        dto.setPet(petDto);

        List<AtendimentoDTO> lista = Collections.singletonList(dto);

        when(atendimentoService.listar()).thenReturn(lista);

        mockMvc.perform(get("/atendimentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Atendimento Teste"))
                .andExpect(jsonPath("$[0].valor").value(100.50))
                .andExpect(jsonPath("$[0].pet.nome").value("Pet Teste"));
    }

    @Test
    public void testGravarAtendimento() throws Exception {
        // Cria um AtendimentoDTO de entrada (sem id)
        AtendimentoDTO inputDto = new AtendimentoDTO();
        inputDto.setDescricao("Novo Atendimento");
        inputDto.setValor(BigDecimal.valueOf(200.75));
        inputDto.setData(LocalDateTime.now());
        PetDTO petDto = new PetDTO();
        petDto.setId(1L);
        petDto.setNome("Pet Novo");
        inputDto.setPet(petDto);

        // Simula a resposta do serviço com um AtendimentoDTO com id atribuído
        AtendimentoDTO outputDto = new AtendimentoDTO();
        outputDto.setId(1L);
        outputDto.setDescricao("Novo Atendimento");
        outputDto.setValor(BigDecimal.valueOf(200.75));
        outputDto.setData(inputDto.getData());
        outputDto.setPet(petDto);

        when(atendimentoService.gravar(any(AtendimentoDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/atendimentos").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Novo Atendimento"))
                .andExpect(jsonPath("$.valor").value(200.75))
                .andExpect(jsonPath("$.pet.nome").value("Pet Novo"));
    }

    @Test
    public void testAtualizarAtendimento() throws Exception {
        // Cria um AtendimentoDTO para atualizar
        AtendimentoDTO inputDto = new AtendimentoDTO();
        inputDto.setDescricao("Atendimento Atualizado");
        inputDto.setValor(BigDecimal.valueOf(150.00));
        inputDto.setData(LocalDateTime.now());
        PetDTO petDto = new PetDTO();
        petDto.setId(1L);
        petDto.setNome("Pet Atualizado");
        inputDto.setPet(petDto);

        // Simula a resposta do serviço para atualização
        AtendimentoDTO outputDto = new AtendimentoDTO();
        outputDto.setId(1L);
        outputDto.setDescricao("Atendimento Atualizado");
        outputDto.setValor(BigDecimal.valueOf(150.00));
        outputDto.setData(inputDto.getData());
        outputDto.setPet(petDto);

        when(atendimentoService.atualizar(any(AtendimentoDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/atendimentos/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Atendimento Atualizado"))
                .andExpect(jsonPath("$.valor").value(150.00))
                .andExpect(jsonPath("$.pet.nome").value("Pet Atualizado"));
    }

    @Test
    public void testDeleteAtendimento() throws Exception {
        // Para o DELETE, assumimos que o serviço apaga o atendimento sem erros.
        mockMvc.perform(delete("/atendimentos/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAtendimentoById() throws Exception {
        // Simula a resposta do serviço para getById
        AtendimentoDTO outputDto = new AtendimentoDTO();
        outputDto.setId(1L);
        outputDto.setDescricao("Atendimento Existente");
        outputDto.setValor(BigDecimal.valueOf(80.00));
        outputDto.setData(LocalDateTime.now());
        PetDTO petDto = new PetDTO();
        petDto.setId(1L);
        petDto.setNome("Pet Existente");
        outputDto.setPet(petDto);

        when(atendimentoService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/atendimentos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Atendimento Existente"))
                .andExpect(jsonPath("$.valor").value(80.00))
                .andExpect(jsonPath("$.pet.nome").value("Pet Existente"));
    }
}
