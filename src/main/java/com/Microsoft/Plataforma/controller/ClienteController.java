package com.Microsoft.Plataforma.controller;

import com.Microsoft.Plataforma.entity.Cliente;
import com.Microsoft.Plataforma.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService service;

    @RequestMapping("/clientes")
    public List<Cliente> list() {
        return service.list();
    }

    @PostMapping("/clientes")
    public void save(@RequestBody Cliente c) {
        service.add(c);
    }

    @DeleteMapping("/clientes/{clienteId}")
    public void delete(@PathVariable long clienteId) {
        service.deleteById(clienteId);
    }

    @RequestMapping("/clientes/{clienteId}")
    public Cliente findById(@PathVariable long clienteId) {
        return service.findById(clienteId).get();
    }

    @PutMapping("/clientes/{clienteId}")
    public void update(@PathVariable long clienteId, @RequestBody Cliente newCliente) {
        Optional<Cliente> oldCliente = service.findById(clienteId);
        if (oldCliente.isPresent()) {
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            cliente.setCpf(newCliente.getCpf());
            cliente.setEmail(newCliente.getEmail());
            service.add(cliente);
        }
    }
}
