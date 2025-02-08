package com.petshop.manager.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_enderecos")
@Getter
@Setter
public class Endereco extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    private String logradouro;
    private String cidade;
    private String bairro;
    private String complemento;
    private String tag;
}
