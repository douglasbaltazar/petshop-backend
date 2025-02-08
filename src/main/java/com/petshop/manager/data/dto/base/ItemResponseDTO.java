package com.petshop.manager.data.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ItemResponseDTO implements Serializable {
    private Integer id;
    private String descricao;
    private String classe;
}