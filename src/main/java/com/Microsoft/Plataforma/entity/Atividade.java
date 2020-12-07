package com.Microsoft.Plataforma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Atividade {

    @GeneratedValue
    @Id
    private long id;

    private String titulo;

    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;
}
