package com.sua_empresa.dao;

import com.sua_empresa.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para cadastrar aluno
    public void cadastrar(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos (nome, idade, curso, matricula) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getMatricula());
            stmt.executeUpdate();
        }
    }

    // Método para listar alunos
    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT nome, idade, curso, matricula FROM alunos";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("curso"),
                    rs.getString("matricula")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return alunos;
    }

    // Método para excluir aluno
    public void excluir(String matricula) throws SQLException {
        String sql = "DELETE FROM alunos WHERE matricula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.executeUpdate();
        }
    }

    // Método para atualizar aluno
    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE alunos SET nome = ?, idade = ?, curso = ? WHERE matricula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getMatricula());
            stmt.executeUpdate();
        }
    }

    // Método para buscar aluno por matrícula
    public Aluno buscarPorMatricula(String matricula) {
        Aluno aluno = null;
        String sql = "SELECT nome, idade, curso FROM alunos WHERE matricula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                aluno = new Aluno(
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("curso"),
                    matricula // Matricula é passada como parâmetro
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public void adicionarAluno(Aluno aluno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarAluno'");
    }

    public List<Aluno> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }
}
