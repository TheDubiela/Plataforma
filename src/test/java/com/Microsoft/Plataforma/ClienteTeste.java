package com.Microsoft.Plataforma;

import com.Microsoft.Plataforma.entity.Cliente;
import com.Microsoft.Plataforma.service.ClienteService;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteTeste {

    @Autowired
    private ClienteService servise;

    @Test
    public void addTest(){
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Marcos");
        cliente1.setCpf("888.888.888-88");
        cliente1.setEmail("marcosMC@Animati.com");

        servise.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Antonio");
        cliente2.setCpf("777.777.777-77");
        cliente2.setEmail("antonioFH@Animati.com");

        servise.add(cliente2);
        assertEquals(2,servise.list().size());
    }

    @Test
    public void listTest(){
        assertEquals(1,servise.list().size());
    }

    @Test
    public void deleteTest(){
        Cliente cliente = servise.findById(1).get();

        servise.delete(cliente);

        assertEquals(0,servise.list().size());
    }

    @Test
    public void findByIdTest(){
        Cliente cliente = servise.findById(1).get();

        assertEquals("Marcos",cliente.getNome());
    }

    @Test
    public void deleteByIdTest(){
        servise.deleteById(2);

        assertEquals(1,servise.list().size());
    }
}
