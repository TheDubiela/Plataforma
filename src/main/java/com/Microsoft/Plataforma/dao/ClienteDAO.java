package com.Microsoft.Plataforma.dao;

import com.Microsoft.Plataforma.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}
