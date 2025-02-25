package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.PetControllerTestConfig;
import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.PetService;
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

@WebMvcTest(PetController.class)
@Import(PetControllerTestConfig.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetService petService;

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
    public void testListarPets() throws Exception {
        // Cria um PetDTO de exemplo
        PetDTO petDto = new PetDTO();
        petDto.setId(1L);
        petDto.setNome("Pet Teste");

        List<PetDTO> lista = Collections.singletonList(petDto);

        // Configura o comportamento do mock para o método listar()
        when(petService.listar()).thenReturn(lista);

        // Realiza a chamada GET e verifica o resultado
        mockMvc.perform(get("/pets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Pet Teste"));
    }

    @Test
    public void testGravarPet() throws Exception {
        // Cria um PetDTO de entrada (sem id)
        PetDTO inputDto = new PetDTO();
        inputDto.setNome("Novo Pet");

        // Simula a resposta do serviço com um PetDTO com id atribuído
        PetDTO outputDto = new PetDTO();
        outputDto.setId(1L);
        outputDto.setNome("Novo Pet");

        when(petService.gravar(any(PetDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/pets").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Novo Pet"));
    }

    @Test
    public void testAtualizarPet() throws Exception {
        // Cria um PetDTO para atualizar
        PetDTO inputDto = new PetDTO();
        inputDto.setNome("Pet Atualizado");

        // Simula a resposta do serviço para atualização
        PetDTO outputDto = new PetDTO();
        outputDto.setId(1L);
        outputDto.setNome("Pet Atualizado");

        when(petService.atualizar(any(PetDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/pets/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pet Atualizado"));
    }

    @Test
    public void testDeletePet() throws Exception {
        // Para o DELETE, assumimos que o serviço apaga o pet sem erros.
        mockMvc.perform(delete("/pets/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPetById() throws Exception {
        // Simula a resposta do serviço para getById
        PetDTO outputDto = new PetDTO();
        outputDto.setId(1L);
        outputDto.setNome("Pet Existente");

        when(petService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pet Existente"));
    }
}
