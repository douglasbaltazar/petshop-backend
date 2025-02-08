package com.petshop.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Usuario extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String cpf;
    private String nome;
    private String perfil;
    private String senha;
}
