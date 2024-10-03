package com.sua_empresa;

import javax.swing.*;

public class TelaBase {
    protected JFrame frame;

    public TelaBase() {
        frame = new JFrame("Sistema de Gerenciamento de Alunos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void esconder() {
        frame.setVisible(false);
    }
}

