package com.Microsoft.Plataforma.controller;

import com.Microsoft.Plataforma.entity.Atividade;
import com.Microsoft.Plataforma.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeServiceImpl;

    @RequestMapping("/atividades")
    public List<Atividade> list() {
        return atividadeServiceImpl.list();
    }

    @PostMapping("/atividades")
    public void save(@RequestBody Atividade a) {
        atividadeServiceImpl.add(a);
    }

    @DeleteMapping("/atividades/{atividadesId}")
    public void delete(@PathVariable long atividadesId) {
        atividadeServiceImpl.deleteById(atividadesId);
    }

    @RequestMapping("/atividades/{atividadesId}")
    public Atividade findById(@PathVariable long atividadesId) {
        return atividadeServiceImpl.findById(atividadesId).get();
    }

    @PutMapping("/atividades/{atividadesId}")
    public void update(@PathVariable long atividadesId, @RequestBody Atividade newAtividade) {
        Optional<Atividade> oldAtividade = atividadeServiceImpl.findById(atividadesId);
        if (oldAtividade.isPresent()) {
            Atividade atividade = oldAtividade.get();
            atividade.setTitulo(newAtividade.getTitulo());
            atividade.setDescricao(newAtividade.getDescricao());
            atividade.setCliente(newAtividade.getCliente());
            atividade.setProjeto(newAtividade.getProjeto());
            atividadeServiceImpl.add(atividade);
        }
    }
}
