// VistaPuntuacionFinal.java
package mvcJuego;

import modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaPuntuacionFinal extends JFrame {

    private JButton btnVolver;

    public VistaPuntuacionFinal(List<Jugador> jugadoresOrdenados) {
        setTitle("Puntuaciones Finales");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("TIMBIRICHE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        add(titulo, BorderLayout.NORTH);

        JPanel panelPuntajes = new JPanel(new GridLayout(4, 1));
        panelPuntajes.setBackground(Color.WHITE);

        for (int i = 0; i < jugadoresOrdenados.size(); i++) {
            Jugador j = jugadoresOrdenados.get(i);
            String posicion = switch (i) {
                case 0 -> "🥇";
                case 1 -> "🥈";
                case 2 -> "🥉";
                default -> "4to";
            };

            JLabel lbl = new JLabel(posicion + " " + j.getNombre() + " - " + j.getPuntos() + " puntos", SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.PLAIN, 20));
            panelPuntajes.add(lbl);
        }

        add(panelPuntajes, BorderLayout.CENTER);

        btnVolver = new JButton("Volver al menú");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 18));
        add(btnVolver, BorderLayout.SOUTH);
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
