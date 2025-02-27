package com.petshop.manager.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petshop.manager.data.dto.base.BaseEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO extends BaseEntityDTO {

    private UsuarioDTO usuario;
    List<ContatoDTO> contatos;
    List<EnderecoDTO> enderecos;
    @JsonIgnore
    List<PetDTO> pets;
}
