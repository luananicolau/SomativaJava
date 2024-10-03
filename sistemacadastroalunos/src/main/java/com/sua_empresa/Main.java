package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Conexão ao banco de dados
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerenciamento_alunos", "postgres", "postgres");
            AlunoDAO alunoDAO = new AlunoDAO(connection);

            String usuario = JOptionPane.showInputDialog("Digite seu tipo de usuário (Admin ou Professor):");

            if ("Admin".equalsIgnoreCase(usuario)) {
                new TelaAdministrador(alunoDAO).setVisible(true);
            } else if ("Professor".equalsIgnoreCase(usuario)) {
                new TelaProfessor(alunoDAO).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário inválido!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + ex.getMessage());
        }
    }
}
