package com.sua_empresa;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;
import com.sua_empresa.reports.RelatorioAluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaProfessor extends JFrame {
    private AlunoDAO alunoDAO;

    public TelaProfessor(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
        setTitle("Tela do Professor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes da interface
        JButton buttonListar = new JButton("Listar Alunos");
        JButton buttonRelatorio = new JButton("Gerar Relatório");

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(buttonListar);
        panel.add(buttonRelatorio);
        add(panel);

        // Ação para listar alunos
        buttonListar.addActionListener(e -> {
            StringBuilder alunosStr = new StringBuilder("Alunos cadastrados:\n");
            for (Aluno aluno : alunoDAO.listarAlunos()) {
                alunosStr.append(aluno.getNome()).append(" - ").append(aluno.getCurso()).append("\n");
            }
            JOptionPane.showMessageDialog(this, alunosStr.toString());
        });

        // Ação para gerar relatório
        buttonRelatorio.addActionListener(e -> {
            // Exemplo de geração de relatório. Ajuste conforme necessário.
            String caminhoArquivo = JOptionPane.showInputDialog("Digite o caminho para salvar o relatório:");
            if (caminhoArquivo != null && !caminhoArquivo.isEmpty()) {
                RelatorioAluno relatorio = new RelatorioAluno(alunoDAO);
                relatorio.gerarRelatorio(caminhoArquivo);
                JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
            }
        });
    }
}
