package com.Microsoft.Plataforma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Cliente {

    @GeneratedValue
    @Id
    private long idCliente;

    private String nome;

    private String cpf;

    private String email;
}
