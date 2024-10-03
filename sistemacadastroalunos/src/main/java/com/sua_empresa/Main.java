package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Alunos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Componentes da interface
        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField(20);
        JLabel labelIdade = new JLabel("Idade:");
        JTextField textIdade = new JTextField(3);
        JLabel labelCurso = new JLabel("Curso:");
        JTextField textCurso = new JTextField(20);
        JLabel labelMatricula = new JLabel("Matrícula:");
        JTextField textMatricula = new JTextField(10);

        JButton buttonCadastrar = new JButton("Cadastrar");
        JButton buttonListar = new JButton("Listar Alunos");
        JButton buttonAtualizar = new JButton("Atualizar Aluno");
        JButton buttonExcluir = new JButton("Excluir Aluno");

        // Layout da interface
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelNome);
        panel.add(textNome);
        panel.add(labelIdade);
        panel.add(textIdade);
        panel.add(labelCurso);
        panel.add(textCurso);
        panel.add(labelMatricula);
        panel.add(textMatricula);
        panel.add(buttonCadastrar);
        panel.add(buttonListar);
        panel.add(buttonAtualizar);
        panel.add(buttonExcluir);

        frame.add(panel);
        frame.setVisible(true);

        // Conexão ao banco de dados
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gerenciamento_alunos", "postgres", "postgres");
            AlunoDAO alunoDAO = new AlunoDAO(connection);

            // Ação para cadastrar aluno
            buttonCadastrar.addActionListener(e -> {
                String nome = textNome.getText();
                int idade;
                try {
                    idade = Integer.parseInt(textIdade.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Idade deve ser um número.");
                    return;
                }
                String curso = textCurso.getText();
                String matricula = textMatricula.getText();

                Aluno aluno = new Aluno(nome, idade, curso, matricula);
                try {
                    alunoDAO.cadastrar(aluno);
                    JOptionPane.showMessageDialog(frame, "Aluno cadastrado com sucesso!");
                    // Limpar campos após cadastro
                    textNome.setText("");
                    textIdade.setText("");
                    textCurso.setText("");
                    textMatricula.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar aluno: " + ex.getMessage());
                }
            });

            // Ação para listar alunos
            buttonListar.addActionListener(e -> {
                List<Aluno> alunos = alunoDAO.listarAlunos(); // Chame o método correto
                StringBuilder alunosStr = new StringBuilder("Alunos cadastrados:\n");
                for (Aluno aluno : alunos) {
                    alunosStr.append(aluno.getNome()).append(" - ").append(aluno.getCurso()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, alunosStr.toString());
            });

            // Ação para atualizar aluno
            buttonAtualizar.addActionListener(e -> {
                String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para atualizar:");
                if (matricula != null && !matricula.isEmpty()) {
                    Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
                    if (aluno != null) {
                        String novoNome = JOptionPane.showInputDialog("Novo nome:", aluno.getNome());
                        int novaIdade;
                        try {
                            novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Nova idade:", aluno.getIdade()));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Idade deve ser um número.");
                            return;
                        }
                        String novoCurso = JOptionPane.showInputDialog("Novo curso:", aluno.getCurso());

                        aluno.setNome(novoNome);
                        aluno.setIdade(novaIdade);
                        aluno.setCurso(novoCurso);

                        try {
                            alunoDAO.atualizar(aluno);
                            JOptionPane.showMessageDialog(frame, "Aluno atualizado com sucesso!");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(frame, "Erro ao atualizar aluno: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Aluno não encontrado!");
                    }
                }
            });

            // Ação para excluir aluno
            buttonExcluir.addActionListener(e -> {
                String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para excluir:");
                if (matricula != null && !matricula.isEmpty()) {
                    try {
                        alunoDAO.excluir(matricula);
                        JOptionPane.showMessageDialog(frame, "Aluno excluído com sucesso!");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Erro ao excluir aluno: " + ex.getMessage());
                    }
                }
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro de conexão com o banco de dados: " + ex.getMessage());
        }
    }
}
