package com.Microsoft.Plataforma.controller;

import com.Microsoft.Plataforma.entity.Atividade;
import com.Microsoft.Plataforma.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    @RequestMapping("/atividades")
    public List<Atividade> list() {
        return service.list();
    }

    @PostMapping("/atividades")
    public void save(@RequestBody Atividade a) {
        service.add(a);
    }

    @DeleteMapping("/atividades/{atividadesId}")
    public void delete(@PathVariable long atividadesId) {
        service.deleteById(atividadesId);
    }

    @RequestMapping("/atividades/{atividadesId}")
    public Atividade findById(@PathVariable long atividadesId) {
        return service.findById(atividadesId).get();
    }

    @PutMapping("/atividades/{atividadesId}")
    public void update(@PathVariable long atividadesId, @RequestBody Atividade newAtividade) {
        Optional<Atividade> oldAtividade = service.findById(atividadesId);
        if (oldAtividade.isPresent()) {
            Atividade atividade = oldAtividade.get();
            atividade.setTitulo(newAtividade.getTitulo());
            atividade.setDescricao(newAtividade.getDescricao());
            atividade.setCliente(newAtividade.getCliente());
            atividade.setProjeto(newAtividade.getProjeto());
            service.add(atividade);
        }
    }
}
