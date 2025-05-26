package mvcJuego;

import modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class VistaJuego extends JFrame {

    private JPanel panelTablero;
    private JButton[][] lineasHorizontales;
    private JButton[][] lineasVerticales;
    private JLabel[][] cuadros;
    private TriConsumer<Integer, Integer, Boolean> clickListener;

    public VistaJuego(int tamanoTablero, List<Jugador> jugadores) {
        setTitle("Timbiriche - Juego");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        panelTablero = new JPanel(new GridLayout(tamanoTablero * 2 + 1, tamanoTablero * 2 + 1));
        lineasHorizontales = new JButton[tamanoTablero + 1][tamanoTablero];
        lineasVerticales = new JButton[tamanoTablero][tamanoTablero + 1];
        cuadros = new JLabel[tamanoTablero][tamanoTablero];

        for (int fila = 0; fila < tamanoTablero * 2 + 1; fila++) {
            for (int col = 0; col < tamanoTablero * 2 + 1; col++) {
                if (fila % 2 == 0 && col % 2 == 1) { // Línea horizontal
                    int f = fila / 2;
                    int c = col / 2;
                    JButton btn = new JButton();
                    btn.setBackground(Color.WHITE);
                    btn.setMargin(new Insets(0, 0, 0, 0));
                    btn.setPreferredSize(new Dimension(40, 10));
                    int finalF = f, finalC = c;
                    btn.addActionListener(e -> {
                        if (clickListener != null) {
                            clickListener.accept(finalF, finalC, true);
                        }
                    });
                    lineasHorizontales[f][c] = btn;
                    panelTablero.add(btn);
                } else if (fila % 2 == 1 && col % 2 == 0) { // Línea vertical
                    int f = fila / 2;
                    int c = col / 2;
                    JButton btn = new JButton();
                    btn.setBackground(Color.WHITE);
                    btn.setMargin(new Insets(0, 0, 0, 0));
                    btn.setPreferredSize(new Dimension(10, 40));
                    int finalF = f, finalC = c;
                    btn.addActionListener(e -> {
                        if (clickListener != null) {
                            clickListener.accept(finalF, finalC, false);
                        }
                    });
                    lineasVerticales[f][c] = btn;
                    panelTablero.add(btn);
                } else if (fila % 2 == 1 && col % 2 == 1) { // Cuadro
                    int f = fila / 2;
                    int c = col / 2;
                    JLabel lbl = new JLabel();
                    lbl.setOpaque(true);
                    lbl.setBackground(Color.WHITE);
                    cuadros[f][c] = lbl;
                    panelTablero.add(lbl);
                } else {
                    panelTablero.add(new JLabel()); // Punto
                }
            }
        }

        add(panelTablero);
    }

    public void setClickListener(TriConsumer<Integer, Integer, Boolean> listener) {
        this.clickListener = listener;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void pintarLinea(int fila, int columna, boolean horizontal, Color color) {
        if (horizontal && lineasHorizontales[fila][columna] != null) {
            lineasHorizontales[fila][columna].setBackground(color);
        } else if (!horizontal && lineasVerticales[fila][columna] != null) {
            lineasVerticales[fila][columna].setBackground(color);
        }
    }

    public void actualizarPuntajes(Map<Jugador, Integer> puntajes) {
        // Implementación opcional: actualizar encabezado/panel lateral con los puntajes
    }

    public void actualizarTablero(boolean[][] horizontales, boolean[][] verticales, Color[][] colores) {
        for (int i = 0; i < horizontales.length; i++) {
            for (int j = 0; j < horizontales[i].length; j++) {
                if (horizontales[i][j]) {
                    lineasHorizontales[i][j].setBackground(Color.GRAY);
                }
            }
        }

        for (int i = 0; i < verticales.length; i++) {
            for (int j = 0; j < verticales[i].length; j++) {
                if (verticales[i][j]) {
                    lineasVerticales[i][j].setBackground(Color.GRAY);
                }
            }
        }

        for (int i = 0; i < colores.length; i++) {
            for (int j = 0; j < colores[i].length; j++) {
                if (colores[i][j] != null) {
                    cuadros[i][j].setBackground(colores[i][j]);
                }
            }
        }
    }
}
