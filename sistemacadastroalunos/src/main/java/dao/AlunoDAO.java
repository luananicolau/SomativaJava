package dao;

import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO() {
        try {
            // Configurar a conexão com o banco de dados
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistema_cadastro_alunos", "usuario", "senha");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, idade, curso, matricula) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("curso"), rs.getString("matricula"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public void excluirAluno(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
