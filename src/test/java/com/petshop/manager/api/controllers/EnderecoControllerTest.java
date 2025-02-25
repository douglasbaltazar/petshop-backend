package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.EnderecoControllerTestConfig;
import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.EnderecoDTO;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.services.EnderecoService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import java.util.List;

@WebMvcTest(EnderecoController.class)
@Import(EnderecoControllerTestConfig.class)
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnderecoService enderecoService;

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
    public void testListarEnderecos() throws Exception {
        // Configura o comportamento do mock para o método listar()
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(1L);
        dto.setLogradouro("Rua Exemplo");
        dto.setCidade("Cidade Exemplo");
        dto.setBairro("Bairro Exemplo");
        dto.setComplemento("Apto 101");
        dto.setTag("Residencial");
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setPerfil("Admin");
        usuarioDto.setNome("Cliente Teste");
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(1L);
        clienteDto.setUsuario(usuarioDto);
        dto.setCliente(clienteDto);
        List<EnderecoDTO> lista = Collections.singletonList(dto);

        Mockito.when(enderecoService.listar()).thenReturn(lista);

        mockMvc.perform(get("/enderecos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].logradouro").value("Rua Exemplo"));
    }

    @Test
    public void testGravarEndereco() throws Exception {
        // Cria um EnderecoDTO de entrada (sem id)
        EnderecoDTO inputDto = new EnderecoDTO();
        inputDto.setLogradouro("Rua Inicial");
        inputDto.setCidade("Cidade Inicial");
        inputDto.setBairro("Bairro Inicial");
        inputDto.setComplemento("Complemento Inicial");
        inputDto.setTag("Trabalho");
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setPerfil("Admin");
        usuarioDto.setNome("Cliente Teste");
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(1L);
        clienteDto.setUsuario(usuarioDto);
        inputDto.setCliente(clienteDto);

        // Simula a resposta do serviço com um EnderecoDTO com id atribuído
        EnderecoDTO outputDto = new EnderecoDTO();
        outputDto.setId(1L);
        outputDto.setLogradouro("Rua Inicial");
        outputDto.setCidade("Cidade Inicial");
        outputDto.setBairro("Bairro Inicial");
        outputDto.setComplemento("Complemento Inicial");
        outputDto.setTag("Trabalho");
        outputDto.setCliente(clienteDto);

        when(enderecoService.gravar(any(EnderecoDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/enderecos").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.logradouro").value("Rua Inicial"));
    }

    @Test
    public void testAtualizarEndereco() throws Exception {
        // Cria um EnderecoDTO para atualização
        EnderecoDTO inputDto = new EnderecoDTO();
        inputDto.setLogradouro("Rua Atualizada");
        inputDto.setCidade("Cidade Atualizada");
        inputDto.setBairro("Bairro Atualizado");
        inputDto.setComplemento("Complemento Atualizado");
        inputDto.setTag("Comercial");
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setPerfil("Admin");
        usuarioDto.setNome("Cliente Teste");
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(1L);
        clienteDto.setUsuario(usuarioDto);
        inputDto.setCliente(clienteDto);

        // Simula a resposta do serviço para atualização
        EnderecoDTO outputDto = new EnderecoDTO();
        outputDto.setId(1L);
        outputDto.setLogradouro("Rua Atualizada");
        outputDto.setCidade("Cidade Atualizada");
        outputDto.setBairro("Bairro Atualizado");
        outputDto.setComplemento("Complemento Atualizado");
        outputDto.setTag("Comercial");
        outputDto.setCliente(clienteDto);

        when(enderecoService.atualizar(any(EnderecoDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/enderecos/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.logradouro").value("Rua Atualizada"))
                .andExpect(jsonPath("$.cidade").value("Cidade Atualizada"));
    }

    @Test
    public void testDeleteEndereco() throws Exception {
        mockMvc.perform(delete("/enderecos/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEnderecoById() throws Exception {
        // Simula a resposta do serviço para getById
        EnderecoDTO outputDto = new EnderecoDTO();
        outputDto.setId(1L);
        outputDto.setLogradouro("Rua Exemplo");
        outputDto.setCidade("Cidade Exemplo");
        outputDto.setBairro("Bairro Exemplo");
        outputDto.setComplemento("Apto 101");
        outputDto.setTag("Residencial");
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setPerfil("Admin");
        usuarioDto.setNome("Cliente Teste");
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(1L);
        clienteDto.setUsuario(usuarioDto);
        outputDto.setCliente(clienteDto);

        when(enderecoService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/enderecos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.logradouro").value("Rua Exemplo"));
    }
}
