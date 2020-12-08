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
    private long idAtividade;

    private String titulo;

    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idProjeto", nullable = false)
    private Projeto projeto;
}
