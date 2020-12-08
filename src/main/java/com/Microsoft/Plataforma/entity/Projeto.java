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
    private long idProjeto;

    private String titulo;

    private String descricao;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "idCliente",nullable = false)
    private Cliente cliente;

}
