package com.petshop.manager.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_racas")
@Getter
@Setter
public class Raca extends BaseEntity {
    private String descricao;
}
