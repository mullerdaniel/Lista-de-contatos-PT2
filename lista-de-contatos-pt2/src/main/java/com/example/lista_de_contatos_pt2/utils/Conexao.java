package com.example.lista_de_contatos_pt2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/listadecontatospt2?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String SENHA = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}
