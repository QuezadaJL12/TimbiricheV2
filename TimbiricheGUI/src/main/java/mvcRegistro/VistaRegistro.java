package mvcRegistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class VistaRegistro extends JFrame {
    private JTextField txtNombre;
    private JButton btnRegistrarse;
    private JLabel lblAvatarSeleccionado;
    private JPanel panelAvatares;

    private String rutaAvatarSeleccionado;

    public VistaRegistro() {
        setTitle("Registro de Jugador");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Registro", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(3, 1));
        txtNombre = new JTextField();
        panelCentro.add(new JLabel("Usuario:", SwingConstants.CENTER));
        panelCentro.add(txtNombre);
        add(panelCentro, BorderLayout.CENTER);

        panelAvatares = new JPanel(new GridLayout(3, 3, 10, 10));
        add(panelAvatares, BorderLayout.SOUTH);
        panelAvatares.setPreferredSize(new Dimension(300, 200));

        btnRegistrarse = new JButton("Registrarse");
        add(btnRegistrarse, BorderLayout.PAGE_END);

        lblAvatarSeleccionado = new JLabel("", SwingConstants.CENTER);
        add(lblAvatarSeleccionado, BorderLayout.WEST);
        cargarAvatares();
        panelAvatares.revalidate();
        panelAvatares.repaint();
        this.validate();
        this.repaint();
    }

    private void cargarAvatares() {
        String[] nombresAvatares = {"GATO.png", "LEIA.png", "LOBO.png", "PINGUINO.png", "RANA.png", "ROBOCOB.png", "SOLDADO.png"};
    for (String nombre : nombresAvatares) {
        String ruta = "Avatares/" + nombre;
        java.net.URL url = getClass().getClassLoader().getResource(ruta);
        System.out.println("Cargando imagen: " + ruta + " -> " + url);
        if (url == null) {
            System.err.println("No se encontró la imagen: " + ruta);
            continue;
        }
        ImageIcon icon = new ImageIcon(url);
        ImageIcon escalado = new ImageIcon(icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        JButton btnAvatar = new JButton(escalado);
        btnAvatar.setActionCommand(nombre);
        btnAvatar.addActionListener(e -> seleccionarAvatar(nombre));
        panelAvatares.add(btnAvatar);
        }
    }

    private void seleccionarAvatar(String nombreArchivo) {
        this.rutaAvatarSeleccionado = "Avatares/" + nombreArchivo;
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(rutaAvatarSeleccionado)));
        lblAvatarSeleccionado.setIcon(new ImageIcon(icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
    }

    public String getNombreJugador() {
        return txtNombre.getText().trim();
    }

    public String getRutaAvatarSeleccionado() {
        return rutaAvatarSeleccionado;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
