package com.petshop.manager.data.dto.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntityDTO {
    private Long id;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;
}
