package com.Microsoft.Plataforma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Projeto {

    @GeneratedValue
    @Id
    private long id;

    private String titulo;

    private String descricao;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "cliente_id",nullable = false)
    private Cliente cliente;

}
