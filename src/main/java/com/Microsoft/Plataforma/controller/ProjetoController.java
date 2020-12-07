package com.Microsoft.Plataforma.controller;

import com.Microsoft.Plataforma.entity.Projeto;
import com.Microsoft.Plataforma.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @RequestMapping("/projetos")
    public List<Projeto> list() {
        return service.list();
    }

    @PostMapping("/projetos")
    public void save(@RequestBody Projeto p) {
        service.add(p);
    }

    @DeleteMapping("/projetos/{projetoId}")
    public void delete(@PathVariable long projetoId) {
        service.deleteById(projetoId);
    }

    @RequestMapping("/projetos/{projetoId}")
    public Projeto findById(@PathVariable long projetoId) {
        return service.findById(projetoId).get();
    }

    @PutMapping("/projetos/{projetoId}")
    public void update(@PathVariable long projetoId, @RequestBody Projeto newProjeto) {
        Optional<Projeto> oldProjeto = service.findById(projetoId);
        if (oldProjeto.isPresent()) {
            Projeto projeto = oldProjeto.get();
            projeto.setTitulo(newProjeto.getTitulo());
            projeto.setDescricao(newProjeto.getDescricao());
            projeto.setStatus(newProjeto.getStatus());
            projeto.setCliente(newProjeto.getCliente());
            service.add(projeto);
        }
    }
}
