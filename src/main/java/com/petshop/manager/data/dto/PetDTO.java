package com.petshop.manager.data.dto;


import com.petshop.manager.data.dto.base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetDTO extends BaseEntityDTO {
    private ClienteDTO cliente;
    private RacaDTO raca;
    private String nome;
    private LocalDate dataNascimento;
}
