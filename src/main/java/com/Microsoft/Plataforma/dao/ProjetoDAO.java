package com.Microsoft.Plataforma.dao;

import com.Microsoft.Plataforma.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoDAO extends JpaRepository<Projeto,Long> {
}
