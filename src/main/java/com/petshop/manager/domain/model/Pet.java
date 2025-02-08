package com.petshop.manager.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pets")
@Getter
@Setter
public class Pet extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_raca", nullable = false)
    private Raca raca;
    private String nome;
    private LocalDate data_nascimento;
}
