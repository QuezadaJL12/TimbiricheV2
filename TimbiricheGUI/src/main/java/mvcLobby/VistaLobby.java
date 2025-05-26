package mvcLobby;

import modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaLobby extends JFrame {

    private JLabel[] lblAvatares;
    private JLabel[] lblNombres;
    private JLabel[] lblPuntajes;
    private JButton btnIniciarPartida;
    private ControladorLobby controlador;

    public VistaLobby(Jugador jugador, int tamanoTablero, boolean esHost, com.mycompany.blackboard.Blackboard blackboard) {
        setTitle("Lobby - Timbiriche");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentesVisuales();
    }

    private void inicializarComponentesVisuales() {
        lblAvatares = new JLabel[4];
        lblNombres = new JLabel[4];
        lblPuntajes = new JLabel[4];
        btnIniciarPartida = new JButton("Iniciar partida");
        btnIniciarPartida.setEnabled(false);

        setLayout(new BorderLayout());

        JPanel panelJugadores = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            lblAvatares[i] = new JLabel();
            lblAvatares[i].setHorizontalAlignment(JLabel.CENTER);

            lblNombres[i] = new JLabel("", JLabel.CENTER);
            lblNombres[i].setFont(new Font("Arial", Font.BOLD, 14));

            lblPuntajes[i] = new JLabel("0", JLabel.CENTER);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(lblAvatares[i], BorderLayout.CENTER);
            panel.add(lblNombres[i], BorderLayout.NORTH);
            panel.add(lblPuntajes[i], BorderLayout.SOUTH);

            panelJugadores.add(panel);
        }

        add(panelJugadores, BorderLayout.CENTER);
        add(btnIniciarPartida, BorderLayout.SOUTH);
    }

    public void mostrarJugadores(List<Jugador> jugadores) {
        for (int i = 0; i < 4; i++) {
            if (i < jugadores.size()) {
                Jugador j = jugadores.get(i);
                lblNombres[i].setText(j.getNombre());
                lblAvatares[i].setIcon(new ImageIcon(getClass().getResource("/Avatares/" + j.getRutaAvatar())));
                lblPuntajes[i].setText("0");
            } else {
                lblNombres[i].setText("");
                lblAvatares[i].setIcon(null);
                lblPuntajes[i].setText("");
            }
        }
    }

    public void setControlador(ControladorLobby controlador) {
        this.controlador = controlador;
    }

    public void setJugador(Jugador jugador) {
        // Se puede guardar el jugador 
    }

    public void setTamanoTablero(int tamano) {
        // Aquí puedes mostrar el tamaño 
    }

    public void setHost(boolean esHost) {
        btnIniciarPartida.setEnabled(esHost);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getBtnIniciarPartida() {
        return btnIniciarPartida;
    }
}
