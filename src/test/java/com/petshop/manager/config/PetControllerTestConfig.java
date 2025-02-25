package com.petshop.manager.config;

import com.petshop.manager.api.security.TokenService;
import com.petshop.manager.domain.repositories.AtendimentoRepository;
import com.petshop.manager.domain.repositories.ClienteRepository;
import com.petshop.manager.domain.repositories.PetRepository;
import com.petshop.manager.domain.repositories.UsuarioRepository;
import com.petshop.manager.domain.services.AtendimentoService;
import com.petshop.manager.domain.services.ClienteService;
import com.petshop.manager.domain.services.PetService;
import com.petshop.manager.domain.services.UsuarioService;
import com.petshop.manager.domain.utils.MapperUtil;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PetControllerTestConfig {
    @Bean
    public AtendimentoService atendimentoService() {
        return Mockito.mock(AtendimentoService.class);
    }

    @Bean
    public MapperUtil mapperUtil() {
        return Mockito.mock(MapperUtil.class);
    }

    @Bean
    public AtendimentoRepository atendimentoRepository() {
        return Mockito.mock(AtendimentoRepository.class);
    }

    @Bean
    public PetService petService() {
        return Mockito.mock(PetService.class);
    }

    @Bean
    public PetRepository petRepository() {
        return Mockito.mock(PetRepository.class);
    }

    @Bean
    public TokenService tokenService() {
        return Mockito.mock(TokenService.class);
    }

    @Bean
    public UsuarioService usuarioService() {
        return Mockito.mock(UsuarioService.class);
    }

    @Bean
    public UsuarioRepository usuarioRepository() {
        return Mockito.mock(UsuarioRepository.class);
    }


    @Bean
    public ClienteService clienteService() {
        return Mockito.mock(ClienteService.class);
    }

    @Bean
    public ClienteRepository clienteRepository() {
        return Mockito.mock(ClienteRepository.class);
    }

}
