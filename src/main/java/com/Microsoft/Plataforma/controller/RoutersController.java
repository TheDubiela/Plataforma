package com.Microsoft.Plataforma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutersController {

    @GetMapping("/clinete/index")
    public String clienteIndex(){
        return "/cliente/index";
    }

    @GetMapping("/projeto/index")
    public String projetoIndex(){
        return "/projeto/index";
    }

    @GetMapping("/atividade/index")
    public String atividadeIndex(){
        return "/atividade/index";
    }

}
