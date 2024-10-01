package view;

import javax.swing.*;
import controller.AlunoController;

public class MainView {
    private AlunoController controller;

    public MainView() {
        controller = new AlunoController();
        criarInterface();
    }

    private void criarInterface() {
        JFrame frame = new JFrame("Cadastro de Alunos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 20, 80, 25);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(20);
        nomeText.setBounds(100, 20, 165, 25);
        panel.add(nomeText);

        // Adicione mais campos para idade, curso e matrícula aqui...

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(10, 80, 150, 25);
        panel.add(cadastrarButton);

        cadastrarButton.addActionListener(e -> {
            String nome = nomeText.getText();
            // Obtenha os outros campos e crie um novo Aluno
            // controller.adicionarAluno(novoAluno);
        });
    }
}
