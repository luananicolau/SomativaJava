package com.sua_empresa.Controller;

import com.sua_empresa.dao.AlunoDAO;
import com.sua_empresa.model.Aluno;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO(null);
    }

    public void adicionarAluno(Aluno aluno) {
        alunoDAO.adicionarAluno(aluno);
    }

    // Adicione mais métodos para listar e excluir alunos aqui...
}
