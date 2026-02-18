package com.example.lista_de_contatos_pt2.repository;

import com.example.lista_de_contatos_pt2.model.Contato;
import com.example.lista_de_contatos_pt2.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepository {

    public Contato salvarContato(Contato contato) throws SQLException {
        String query = "INSERT INTO contato (nome, telefone) VALUES (?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                contato.setId(rs.getInt(1));
                return contato;
            }

        }
    return null;
    }


    public List<Contato> listarContatos() throws SQLException {
        List<Contato> listaDeContatos = new ArrayList<>();
        String query = "SELECT id, nome, telefone FROM contato";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

                while(rs.next()) {

                    listaDeContatos.add(new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone")
                    ));
                }
        }
        return listaDeContatos;
    }


    public void atualizarContato(Contato contato) throws SQLException {
        String query = """
                UPDATE contato
                SET nome = ?,
                telefone = ?
                WHERE 
                id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setInt(3, contato.getId());
            stmt.executeUpdate();
        }
    }


    public void deletarContato(int id) throws SQLException {
        String query = "DELETE FROM contato WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Nenhum contato foi deletado!");
            }
        }
    }





    public boolean contatoExiste(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM contato WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }



}
