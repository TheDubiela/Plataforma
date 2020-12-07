package com.Microsoft.Plataforma.service;

import com.Microsoft.Plataforma.dao.ClienteDAO;
import com.Microsoft.Plataforma.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteDAO dao;

    @Override
    public void add(Cliente cliente) {
        dao.save(cliente);
    }

    @Override
    public List<Cliente> list() {
        return dao.findAll();
    }

    @Override
    public void delete(Cliente cliente) {
        dao.delete(cliente);
    }

    @Override
    public Optional<Cliente> findById(long clienteId) {
        return dao.findById(clienteId);
    }

    @Override
    public void deleteById(long clienteId) {
        dao.deleteById(clienteId);
    }
}
