package com.Microsoft.Plataforma.service;

import com.Microsoft.Plataforma.dao.ProjetoDAO;
import com.Microsoft.Plataforma.entity.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService{

    @Autowired
    private ProjetoDAO dao;

    @Override
    public void add(Projeto projeto) {
        dao.save(projeto);
    }

    @Override
    public List<Projeto> list() {
        return dao.findAll();
    }

    @Override
    public void delete(Projeto projeto) {
        dao.delete(projeto);
    }

    @Override
    public Optional<Projeto> findById(long projetoId) {
        return dao.findById(projetoId);
    }

    @Override
    public void deleteById(long projetoId) {
        dao.deleteById(projetoId);
    }
}
