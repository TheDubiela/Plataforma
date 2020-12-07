package com.Microsoft.Plataforma.service;

import com.Microsoft.Plataforma.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    void add(Cliente cliente);

    List<Cliente> list();

    void delete(Cliente cliente);

    Optional<Cliente> findById(long clienteId);

    void deleteById(long clienteId);
}
