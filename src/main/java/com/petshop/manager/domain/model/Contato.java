package com.petshop.manager.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_contatos")
@Getter
@Setter
public class Contato extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    private String tag;
    private String tipo;
}
