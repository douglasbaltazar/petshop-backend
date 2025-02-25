package com.petshop.manager.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.manager.config.ContatoControllerTestConfig;
import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.ContatoDTO;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.ContatoRepository;
import com.petshop.manager.domain.services.ContatoService;
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

@WebMvcTest(ContatoController.class)
@Import(ContatoControllerTestConfig.class)
public class ContatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContatoService contatoService;

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
    public void testListarContatos() throws Exception {
        // Configura o comportamento do mock para o método listar()
        ContatoDTO dto = new ContatoDTO();
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setPerfil("Admin");
        usuarioDto.setNome("Contato Teste");
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setUsuario(usuarioDto);
        dto.setId(1L);
        dto.setCliente(clienteDTO);
        List<ContatoDTO> lista = Collections.singletonList(dto);

        Mockito.when(contatoService.listar()).thenReturn(lista);

        mockMvc.perform(get("/contatos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void testGravarContato() throws Exception {
        // Cria um ContatoDTO de entrada (sem id)
        ContatoDTO inputDto = new ContatoDTO();
        UsuarioDTO usuarioDto = new UsuarioDTO();
        ClienteDTO clienteDTO = new ClienteDTO();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Contato Teste");
        usuarioDto.setPerfil("Admin");
        clienteDTO.setUsuario(usuarioDto);

        inputDto.setCliente(clienteDTO);

        // Simula a resposta do serviço com um ContatoDTO que possui id atribuído
        ContatoDTO outputDto = new ContatoDTO();
        outputDto.setId(1L);
        outputDto.setCliente(clienteDTO);

        when(contatoService.gravar(any(ContatoDTO.class), anyLong())).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        // Note que o endpoint POST requer o id do cliente na URL (ex: /contatos/1)
        mockMvc.perform(post("/contatos/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testAtualizarContato() throws Exception {
        // Cria um ContatoDTO para atualizar
        ContatoDTO inputDto = new ContatoDTO();
        UsuarioDTO usuarioDto = new UsuarioDTO();
        ClienteDTO clienteDTO = new ClienteDTO();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Contato Teste");
        usuarioDto.setPerfil("Admin");
        clienteDTO.setUsuario(usuarioDto);
        inputDto.setCliente(clienteDTO);

        // Simula a resposta do serviço para atualização
        ContatoDTO outputDto = new ContatoDTO();
        outputDto.setId(1L);
        usuarioDto.setNome("Contato Atualizado");
        outputDto.setCliente(clienteDTO);

        when(contatoService.atualizar(any(ContatoDTO.class))).thenReturn(outputDto);

        String jsonBody = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(put("/contatos/1").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteContato() throws Exception {
        mockMvc.perform(delete("/contatos/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetContatoById() throws Exception {
        // Simula a resposta do serviço para getById
        ContatoDTO outputDto = new ContatoDTO();
        ClienteDTO clienteDTO = new ClienteDTO();
        outputDto.setId(1L);
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setId(1L);
        usuarioDto.setNome("Contato Teste");
        usuarioDto.setPerfil("Admin");
        clienteDTO.setUsuario(usuarioDto);
        outputDto.setCliente(clienteDTO);

        when(contatoService.getDto(1L)).thenReturn(outputDto);

        mockMvc.perform(get("/contatos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
