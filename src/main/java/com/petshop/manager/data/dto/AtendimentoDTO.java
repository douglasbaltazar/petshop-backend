package com.petshop.manager.data.dto;

import com.petshop.manager.data.dto.base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AtendimentoDTO extends BaseEntityDTO {
    private PetDTO pet;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;
}
