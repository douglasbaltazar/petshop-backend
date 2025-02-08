package com.petshop.manager.data.dto;

import com.petshop.manager.data.dto.base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO extends BaseEntityDTO {
    private String cpf;
    private String nome;
    private String perfil;
    private String senha;
}
