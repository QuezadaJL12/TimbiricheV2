package mvcTamanoTablero;

import javax.swing.*;
import java.awt.*;

public class VistaTamanoTablero extends JFrame {

    private final JButton btnTam10;
    private final JButton btnTam20;
    private final JButton btnTam30;
    private final JButton btnServidor;
    private final JButton btnUnirse;

    public VistaTamanoTablero() {
        setTitle("Seleccionar Tamaño del Tablero");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        btnTam10 = new JButton("Tablero 10x10");
        btnTam20 = new JButton("Tablero 20x20");
        btnTam30 = new JButton("Tablero 30x30");
        btnServidor = new JButton("Crear Servidor");
        btnUnirse = new JButton("Unirse al Servidor");

        add(btnTam10);
        add(btnTam20);
        add(btnTam30);
        add(btnServidor);
        add(btnUnirse);
    }

    public JButton getBtnTam10() {
        return btnTam10;
    }

    public JButton getBtnTam20() {
        return btnTam20;
    }

    public JButton getBtnTam30() {
        return btnTam30;
    }

    public JButton getBtnServidor() {
        return btnServidor;
    }

    public JButton getBtnUnirse() {
        return btnUnirse;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
