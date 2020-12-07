package com.Microsoft.Plataforma.service;

import com.Microsoft.Plataforma.dao.AtividadeDAO;
import com.Microsoft.Plataforma.entity.Atividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeServiceImpl implements AtividadeService{

    @Autowired
    private AtividadeDAO dao;

    @Override
    public void add(Atividade atividade) {
        dao.save(atividade);
    }

    @Override
    public List<Atividade> list() {
        return dao.findAll();
    }

    @Override
    public void delete(Atividade atividade) {
        dao.delete(atividade);
    }

    @Override
    public Optional<Atividade> findById(long atividadeId) {
        return dao.findById(atividadeId);
    }

    @Override
    public void deleteById(long atividadeId) {
        dao.deleteById(atividadeId);
    }
}
