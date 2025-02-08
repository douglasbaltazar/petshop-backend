package com.petshop.manager.data.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO implements Serializable {
    private List<ItemResponseDTO> lista;
    private String mensagem;

    public ResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }
}