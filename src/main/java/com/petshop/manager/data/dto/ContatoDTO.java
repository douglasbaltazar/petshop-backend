package com.petshop.manager.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petshop.manager.data.dto.base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ContatoDTO extends BaseEntityDTO {
    @JsonIgnore
    private ClienteDTO cliente;
    private String tag;
    private String tipo;
}
