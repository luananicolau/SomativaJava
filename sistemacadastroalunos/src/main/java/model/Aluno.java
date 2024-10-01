package model;

public class Aluno {
    private int id;
    private String nome;
    private int idade;
    private String curso;
    private String matricula;

    public Aluno(int id, String nome, int idade, String curso, String matricula) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
        this.matricula = matricula;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCurso() {
        return curso;
    }

    public String getMatricula() {
        return matricula;
    }
}
