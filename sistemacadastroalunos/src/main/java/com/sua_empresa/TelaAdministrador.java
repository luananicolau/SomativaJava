package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaAdministrador extends JFrame {
    private AlunoDAO alunoDAO;

    public TelaAdministrador(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
        setTitle("Tela do Administrador");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes da interface
        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField(20);
        JLabel labelIdade = new JLabel("Idade:");
        JTextField textIdade = new JTextField(3);
        JLabel labelCurso = new JLabel("Curso:");
        JTextField textCurso = new JTextField(20);
        JLabel labelMatricula = new JLabel("Matrícula:");
        JTextField textMatricula = new JTextField(10);

        JButton buttonCadastrar = new JButton("Cadastrar Aluno");
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

        add(panel);

        // Ação para cadastrar aluno
        buttonCadastrar.addActionListener(e -> {
            String nome = textNome.getText();
            int idade = Integer.parseInt(textIdade.getText());
            String curso = textCurso.getText();
            String matricula = textMatricula.getText();

            Aluno aluno = new Aluno(nome, idade, curso, matricula);
            try {
                alunoDAO.cadastrar(aluno);
                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                clearFields(textNome, textIdade, textCurso, textMatricula);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar aluno: " + ex.getMessage());
            }
        });

        // Ação para listar alunos
        buttonListar.addActionListener(e -> {
            StringBuilder alunosStr = new StringBuilder("Alunos cadastrados:\n");
            for (Aluno aluno : alunoDAO.listarAlunos()) {
                alunosStr.append(aluno.getNome()).append(" - ").append(aluno.getCurso()).append("\n");
            }
            JOptionPane.showMessageDialog(this, alunosStr.toString());
        });

        // Ação para atualizar aluno
        buttonAtualizar.addActionListener(e -> {
            String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para atualizar:");
            if (matricula != null && !matricula.isEmpty()) {
                Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
                if (aluno != null) {
                    String novoNome = JOptionPane.showInputDialog("Novo nome:", aluno.getNome());
                    int novaIdade = Integer.parseInt(JOptionPane.showInputDialog("Nova idade:", aluno.getIdade()));
                    String novoCurso = JOptionPane.showInputDialog("Novo curso:", aluno.getCurso());

                    aluno.setNome(novoNome);
                    aluno.setIdade(novaIdade);
                    aluno.setCurso(novoCurso);

                    try {
                        alunoDAO.atualizar(aluno);
                        JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao atualizar aluno: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
                }
            }
        });

        // Ação para excluir aluno
        buttonExcluir.addActionListener(e -> {
            String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno para excluir:");
            if (matricula != null && !matricula.isEmpty()) {
                try {
                    alunoDAO.excluir(matricula);
                    JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir aluno: " + ex.getMessage());
                }
            }
        });
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
