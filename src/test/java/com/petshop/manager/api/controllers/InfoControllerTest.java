package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.InfoControllerTestConfig;
import com.petshop.manager.data.dto.InfoDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.InfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
@Import(InfoControllerTestConfig.class)
public class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InfoService infoService;

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
    public void testGetInfo() throws Exception {
        // Cria um objeto InfoDTO com valores de exemplo
        InfoDTO infoDto = new InfoDTO();
        infoDto.setId(1L);
        infoDto.setValorArrecadado(BigDecimal.valueOf(1000.0));
        infoDto.setNumPets(10L);
        infoDto.setNumRacas(5L);
        infoDto.setNumClientes(20L);
        infoDto.setNumAtendimentos(15L);

        // Configura o comportamento do mock do service
        when(infoService.getInfos()).thenReturn(infoDto);

        // Realiza a chamada GET e verifica o resultado
        mockMvc.perform(get("/infos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.valorArrecadado").value(1000.0))
                .andExpect(jsonPath("$.numPets").value(10))
                .andExpect(jsonPath("$.numRacas").value(5))
                .andExpect(jsonPath("$.numClientes").value(20))
                .andExpect(jsonPath("$.numAtendimentos").value(15));
    }
}
