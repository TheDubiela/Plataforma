package com.Microsoft.Plataforma.dao;

import com.Microsoft.Plataforma.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeDAO extends JpaRepository<Atividade,Long> {
}
