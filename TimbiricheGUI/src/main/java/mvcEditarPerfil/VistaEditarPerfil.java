package mvcEditarPerfil;

import modelo.Jugador;

import javax.swing.*;
import java.awt.*;

public class VistaEditarPerfil extends JFrame {
    private JTextField txtNombre;
    private JButton[] botonesAvatar;
    private JButton btnAceptar, btnCancelar;
    private JLabel lblAvatarSeleccionado;

    public VistaEditarPerfil() {
        setTitle("Editar Perfil");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 20, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 20, 200, 25);
        add(txtNombre);

        JLabel lblAvatar = new JLabel("Avatar:");
        lblAvatar.setBounds(50, 60, 100, 25);
        add(lblAvatar);

        JPanel panelAvatares = new JPanel(new GridLayout(4, 4, 5, 5));
        panelAvatares.setBounds(50, 90, 300, 200);
        botonesAvatar = new JButton[16];

        for (int i = 0; i < botonesAvatar.length; i++) {
            String nombre = "avatar" + (i + 1) + ".png";
            botonesAvatar[i] = new JButton(new ImageIcon("/Avatares/" + nombre));
            botonesAvatar[i].setActionCommand(nombre);
            panelAvatares.add(botonesAvatar[i]);
        }
        add(panelAvatares);

        lblAvatarSeleccionado = new JLabel();
        lblAvatarSeleccionado.setBounds(370, 90, 100, 100);
        add(lblAvatarSeleccionado);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(150, 310, 100, 30);
        add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 310, 100, 30);
        add(btnCancelar);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JButton[] getBotonesAvatar() {
        return botonesAvatar;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JLabel getLblAvatarSeleccionado() {
        return lblAvatarSeleccionado;
    }
}