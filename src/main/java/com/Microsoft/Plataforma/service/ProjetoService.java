package com.Microsoft.Plataforma.service;

import com.Microsoft.Plataforma.entity.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    void add(Projeto projeto);

    List<Projeto> list();

    void delete(Projeto projeto);

    Optional<Projeto> findById(long projetoId);

    void deleteById(long projetoId);
}
