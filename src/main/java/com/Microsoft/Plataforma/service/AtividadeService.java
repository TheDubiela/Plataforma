package com.Microsoft.Plataforma.service;

import com.Microsoft.Plataforma.entity.Atividade;

import java.util.List;
import java.util.Optional;

public interface AtividadeService {
    void add(Atividade atividade);

    List<Atividade> list();

    void delete(Atividade atividade);

    Optional<Atividade> findById(long atividadeId);

    void deleteById(long atividadeId);
}
