package com.petshop.manager.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_atendimentos")
@Getter
@Setter
public class Atendimento extends BaseEntity {
    @OneToOne
    @JoinColumn(name ="pet_id", nullable = false, unique = false)
    private Pet pet;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;
}
