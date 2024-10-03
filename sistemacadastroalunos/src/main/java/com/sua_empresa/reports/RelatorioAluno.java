package com.sua_empresa.reports;

import com.sua_empresa.model.Aluno;
import com.sua_empresa.dao.AlunoDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioAluno {
    private AlunoDAO alunoDAO;

    public RelatorioAluno(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void gerarRelatorio(String caminhoArquivo) {
        List<Aluno> alunos = alunoDAO.listarAlunos();

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write("Relatório de Alunos\n");
            writer.write("-------------------\n");

            for (Aluno aluno : alunos) {
                writer.write("Nome: " + aluno.getNome() + "\n");
                writer.write("Idade: " + aluno.getIdade() + "\n");
                writer.write("Curso: " + aluno.getCurso() + "\n");
                writer.write("Matrícula: " + aluno.getMatricula() + "\n");
                writer.write("-------------------\n");
            }

            System.out.println("Relatório gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }
}
