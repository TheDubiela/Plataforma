package com.Microsoft.Plataforma;

import com.Microsoft.Plataforma.entity.Cliente;
import com.Microsoft.Plataforma.entity.Projeto;
import com.Microsoft.Plataforma.service.ClienteService;
import com.Microsoft.Plataforma.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ProjetoTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProjetoService projetoService;

    private void initCliente(){
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Marcos");
        cliente1.setCpf("888.888.888-88");
        cliente1.setEmail("marcosMC@Animati.com");

        clienteService.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Antonio");
        cliente2.setCpf("777.777.777-77");
        cliente2.setEmail("antonioFH@Animati.com");

        clienteService.add(cliente2);
    }

    @Test
    public void addTest(){
        initCliente();

        Projeto projeto1 = new Projeto();
        projeto1.setTitulo("Dev. Mobile Application");
        projeto1.setCliente(clienteService.findById(1).get());
        projeto1.setDescricao("development of a mobile application");
        projeto1.setStatus("ongoing");

        projetoService.add(projeto1);

        Projeto projeto2 = new Projeto();

        projeto2.setTitulo("Dev. Web Application");
        projeto2.setCliente(clienteService.findById(2).get());
        projeto2.setDescricao("development of a web application");
        projeto2.setStatus("ongoing");

        projetoService.add(projeto2);


        assertEquals(2,projetoService.list().size());
    }

    @Test
    public void listTest(){
        assertEquals(1,projetoService.list().size());
    }

    @Test
    public void deleteTest(){
        Projeto projeto = projetoService.findById(3).get();

        projetoService.delete(projeto);

        assertEquals(0,projetoService.list().size());
    }

    @Test
    public void findByIdTest(){
        Projeto projeto = projetoService.findById(3).get();

        assertEquals("Dev. Mobile Application",projeto.getTitulo());
    }

    @Test
    public void deleteByIdTest(){
        projetoService.deleteById(4);

        assertEquals(1,projetoService.list().size());
    }
}
