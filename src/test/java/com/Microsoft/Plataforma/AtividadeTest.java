package com.Microsoft.Plataforma;

import com.Microsoft.Plataforma.entity.Atividade;
import com.Microsoft.Plataforma.entity.Cliente;
import com.Microsoft.Plataforma.entity.Projeto;
import com.Microsoft.Plataforma.service.AtividadeService;
import com.Microsoft.Plataforma.service.ClienteService;
import com.Microsoft.Plataforma.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class AtividadeTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private AtividadeService atividadeService;

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

    private void initProjeto(){
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
    }

    @Test
    public void addTest(){
        initProjeto();

        Atividade atividade1 = new Atividade();
        atividade1.setTitulo("User Experience");
        atividade1.setDescricao("tests for user experience and adaptability");
        atividade1.setProjeto(projetoService.findById(3).get());
        atividade1.setCliente(clienteService.findById(1).get());

        atividadeService.add(atividade1);

        Atividade atividade2 = new Atividade();
        atividade2.setTitulo("CSS development");
        atividade2.setDescricao("development of a Theme for the web-site");
        atividade2.setProjeto(projetoService.findById(4).get());
        atividade2.setCliente(clienteService.findById(2).get());

        atividadeService.add(atividade2);

        assertEquals(2,atividadeService.list().size());
    }

    @Test
    public void listTest(){
        assertEquals(1,atividadeService.list().size());
    }

    @Test
    public void deleteTest(){
        Atividade atividade = atividadeService.findById(5).get();

        atividadeService.delete(atividade);

        assertEquals(0,atividadeService.list().size());
    }

    @Test
    public void findByIdTest(){
        Atividade atividade = atividadeService.findById(5).get();

        assertEquals("User Experience",atividade.getTitulo());
    }

    @Test
    public void deleteByIdTest(){
        atividadeService.deleteById(6);

        assertEquals(1,atividadeService.list().size());
    }
}
