package com.petshop.manager.data.dto.base;

import com.petshop.manager.data.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntityDTO {
    private Long id;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;
    private UsuarioDTO criadoPor;
}
