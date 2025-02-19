package com.petshop.manager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class InfoDTO {
    Long id;
    BigDecimal valorArrecadado;
    private Long numPets;
    private Long numRacas;
    private Long numClientes;
    private Long numAtendimentos;

    public InfoDTO(Long id, BigDecimal valorArrecadado, Long numPets,
                   Long numRacas, Long numClientes, Long numAtendimentos) {
        this.id = id;
        this.valorArrecadado = valorArrecadado;
        this.numPets = numPets;
        this.numRacas = numRacas;
        this.numClientes = numClientes;
        this.numAtendimentos = numAtendimentos;
    }
}
