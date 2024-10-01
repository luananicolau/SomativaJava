package com.sua_empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/seu_banco"; // Substitua pelo seu banco
        String usuario = "usuario"; // Substitua pelo seu usuário
        String senha = "senha"; // Substitua pela sua senha

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            AlunoDAO alunoDAO = new AlunoDAO(connection);
            Aluno aluno = new Aluno("João Silva", 20, "Ciência da Computação", "123456");
            alunoDAO.cadastrar(aluno);
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
