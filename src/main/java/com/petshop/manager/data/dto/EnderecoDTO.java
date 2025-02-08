package com.petshop.manager.data.dto;

import com.petshop.manager.data.dto.base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO extends BaseEntityDTO {
    private ClienteDTO cliente;
    private String logradouro;
    private String cidade;
    private String bairro;
    private String complemento;
    private String tag;
}
