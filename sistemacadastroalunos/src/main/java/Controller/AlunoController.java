package controller;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    public void adicionarAluno(Aluno aluno) {
        alunoDAO.adicionarAluno(aluno);
    }

    // Adicione mais métodos para listar e excluir alunos aqui...
}
