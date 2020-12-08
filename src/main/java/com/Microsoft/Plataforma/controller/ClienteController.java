package com.Microsoft.Plataforma.controller;

import com.Microsoft.Plataforma.entity.Cliente;
import com.Microsoft.Plataforma.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteServiceImpl;

    @RequestMapping("/clientes")
    public List<Cliente> list() {
        return clienteServiceImpl.list();
    }

    @PostMapping("/clientes")
    public void save(@RequestBody Cliente c) {
        clienteServiceImpl.add(c);
    }

    @DeleteMapping("/clientes/{clienteId}")
    public void delete(@PathVariable long clienteId) {
        clienteServiceImpl.deleteById(clienteId);
    }

    @RequestMapping("/clientes/{clienteId}")
    public Cliente findById(@PathVariable long clienteId) {
        return clienteServiceImpl.findById(clienteId).get();
    }

    @PutMapping("/clientes/{clienteId}")
    public void update(@PathVariable long clienteId, @RequestBody Cliente newCliente) {
        Optional<Cliente> oldCliente = clienteServiceImpl.findById(clienteId);
        if (oldCliente.isPresent()) {
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            cliente.setCpf(newCliente.getCpf());
            cliente.setEmail(newCliente.getEmail());
            clienteServiceImpl.add(cliente);
        }
    }
}
