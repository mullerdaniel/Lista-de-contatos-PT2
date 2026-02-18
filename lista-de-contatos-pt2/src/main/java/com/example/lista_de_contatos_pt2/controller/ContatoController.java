package com.example.lista_de_contatos_pt2.controller;

import com.example.lista_de_contatos_pt2.model.Contato;
import com.example.lista_de_contatos_pt2.repository.ContatoRepository;
import com.example.lista_de_contatos_pt2.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/listadecontatospt2")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    // POST
    @PostMapping
    public Contato postContato(
            @RequestBody Contato contato
    ) {
        try {
            return contatoService.salvarContato(contato);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @GetMapping
    public List<Contato> getContato() {
        try {
            return contatoService.listarContatos();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    @PutMapping("/{id}")
    public Contato updateContato(
            @PathVariable int id,
            @RequestBody Contato contato
    ){
        try{
            return contato = contatoService.atualizarContato(contato,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public void deletarContato(
            @PathVariable int id

    ){
        try{
            contatoService.deletarContato(id);

        }catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
