package com.petshop.manager.api.controllers;

import com.petshop.manager.api.security.TokenService;
import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.data.dto.auth.LoginRequestDTO;
import com.petshop.manager.data.dto.auth.RegisterRequestDTO;
import com.petshop.manager.data.dto.auth.ResponseAuthDTO;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.UsuarioRepository;
import com.petshop.manager.domain.services.ClienteService;
import com.petshop.manager.domain.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    private MapperUtil mapper;

    @Autowired
    private ClienteService clienteService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario user = this.usuarioRepository.findByCpf(body.cpf()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseAuthDTO(user.getNome(), token, user.getPerfil()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<Usuario> user = this.usuarioRepository.findByCpf(body.cpf());

        if(user.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setSenha(passwordEncoder.encode(body.password()));
            newUser.setCpf(body.cpf());
            newUser.setNome(body.nome());
            newUser.setPerfil(body.perfil());

            newUser = this.usuarioRepository.save(newUser);

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setUsuario(mapper.map(newUser, UsuarioDTO.class));
            clienteService.gravar(clienteDTO);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseAuthDTO(newUser.getNome(), token, newUser.getPerfil()));
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String token) {
        String isValid = tokenService.validateToken(token);

        if(isValid != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(false);
        }
    }
}
