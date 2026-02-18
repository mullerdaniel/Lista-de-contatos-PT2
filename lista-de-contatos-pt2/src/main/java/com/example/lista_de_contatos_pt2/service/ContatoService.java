package com.example.lista_de_contatos_pt2.service;

import com.example.lista_de_contatos_pt2.model.Contato;
import com.example.lista_de_contatos_pt2.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }


    public Contato salvarContato(Contato contato) throws SQLException {
        return contatoRepository.salvarContato(contato);
    }


    public List<Contato> listarContatos() throws SQLException {
        return contatoRepository.listarContatos();
    }

    public Contato atualizarContato(Contato contato, int id) throws SQLException {
        contato.setId(id);
        contatoRepository.atualizarContato(contato);

        return contato;
    }

    public void deletarContato(int id) throws SQLException {
        if(!contatoRepository.contatoExiste(id)) {
            throw new RuntimeException("Não tem contatos cadastrados!");
        }
    }
}
