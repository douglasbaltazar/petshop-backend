package com.petshop.manager.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends  BaseEntity {
    @OneToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf", unique = true, nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Contato> contatos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pet> pets;

}
