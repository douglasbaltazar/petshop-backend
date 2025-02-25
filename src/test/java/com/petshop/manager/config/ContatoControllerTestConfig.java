package com.petshop.manager.config;

import com.petshop.manager.api.security.TokenService;
import com.petshop.manager.domain.repositories.ClienteRepository;
import com.petshop.manager.domain.repositories.ContatoRepository;
import com.petshop.manager.domain.repositories.UsuarioRepository;
import com.petshop.manager.domain.services.ClienteService;
import com.petshop.manager.domain.services.ContatoService;
import com.petshop.manager.domain.services.UsuarioService;
import com.petshop.manager.domain.utils.MapperUtil;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class ContatoControllerTestConfig {
    @Bean
    public ClienteService clienteService() {
        return Mockito.mock(ClienteService.class);
    }

    @Bean
    public MapperUtil mapperUtil() {
        return Mockito.mock(MapperUtil.class);
    }

    @Bean
    public ClienteRepository clienteRepository() {
        return Mockito.mock(ClienteRepository.class);
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public TokenService tokenService() {
        return Mockito.mock(TokenService.class);
    }

    @Bean
    public ContatoService contatoService() {
        return Mockito.mock(ContatoService.class);
    }
    @Bean
    public ContatoRepository contatoRepository() {
        return Mockito.mock(ContatoRepository.class);
    }

}
