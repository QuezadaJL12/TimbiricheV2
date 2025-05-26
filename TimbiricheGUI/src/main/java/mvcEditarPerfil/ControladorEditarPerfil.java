package mvcEditarPerfil;

import modelo.Jugador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorEditarPerfil {
    private VistaEditarPerfil vista;
    private ModeloEditarPerfil modelo;
    private Jugador jugadorOriginal;
    private PerfilEditadoListener listener;

    public ControladorEditarPerfil(VistaEditarPerfil vista, ModeloEditarPerfil modelo, Jugador jugadorOriginal, PerfilEditadoListener listener) {
        this.vista = vista;
        this.modelo = modelo;
        this.jugadorOriginal = jugadorOriginal;
        this.listener = listener;

        vista.getTxtNombre().setText(jugadorOriginal.getNombre());
        vista.getLblAvatarSeleccionado().setIcon(jugadorOriginal.getAvatarIcon());

        for (JButton btn : vista.getBotonesAvatar()) {
            btn.addActionListener(e -> {
                ImageIcon icon = new ImageIcon("/Avatares/" + btn.getActionCommand());
                vista.getLblAvatarSeleccionado().setIcon(icon);
                vista.getLblAvatarSeleccionado().putClientProperty("ruta", btn.getActionCommand());
            });
        }

        vista.getBtnAceptar().addActionListener(e -> {
            String nuevoNombre = vista.getTxtNombre().getText().trim();
            Object ruta = vista.getLblAvatarSeleccionado().getClientProperty("ruta");

            if (nuevoNombre.isEmpty() || ruta == null) {
                JOptionPane.showMessageDialog(vista, "Nombre y avatar son obligatorios.");
                return;
            }

            jugadorOriginal.setNombre(nuevoNombre);
            jugadorOriginal.setRutaAvatar((String) ruta);

            listener.perfilEditado(jugadorOriginal);
            vista.dispose();
        });

        vista.getBtnCancelar().addActionListener(e -> vista.dispose());
    }
}