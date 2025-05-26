package mvcJuego;

import modelo.Cuadro;
import modelo.Jugador;
import modelo.Linea;
import modelo.Tablero;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloJuego {

    private final Tablero tablero;
    private final List<Jugador> jugadores;
    private int turnoActual;

    public ModeloJuego(List<Jugador> jugadores, int tamano) {
        this.tablero = new Tablero(tamano);
        this.jugadores = jugadores;
        this.turnoActual = 0;
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoActual);
    }

    public boolean sePuedeTrazarLinea(int fila, int columna, boolean horizontal) {
        return tablero.sePuedeTrazarLinea(fila, columna, horizontal);
    }

    public boolean seFormoCuadro(int fila, int columna, boolean horizontal, Jugador jugador) {
        List<Cuadro> cuadros = tablero.trazarLineaYObtenerCuadros(fila, columna, horizontal, jugador);
        for (Cuadro c : cuadros) {
            jugador.setPuntos(jugador.getPuntos() + 1);
        }
        return !cuadros.isEmpty();
    }

    public void siguienteTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public boolean juegoFinalizado() {
        return tablero.estaCompleto();
    }

    public Linea[][] getLineasHorizontales() {
        return tablero.getLineasHorizontales();
    }

    public Linea[][] getLineasVerticales() {
        return tablero.getLineasVerticales();
    }

    public Color[][] getColoresCuadros() {
        return tablero.getColoresCuadros();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Map<Jugador, Integer> getPuntajes() {
        Map<Jugador, Integer> mapa = new HashMap<>();
        for (Jugador j : jugadores) {
            mapa.put(j, j.getPuntos());
        }
        return mapa;
    }

    public boolean[][] getLineasHorizontalesDibujadas() {
        Linea[][] lineas = tablero.getLineasHorizontales();
        boolean[][] dibujadas = new boolean[lineas.length][lineas[0].length];
        for (int i = 0; i < lineas.length; i++) {
            for (int j = 0; j < lineas[i].length; j++) {
                dibujadas[i][j] = lineas[i][j].estaDibujada();
            }
        }
        return dibujadas;
    }

    public boolean[][] getLineasVerticalesDibujadas() {
        Linea[][] lineas = tablero.getLineasVerticales();
        boolean[][] dibujadas = new boolean[lineas.length][lineas[0].length];
        for (int i = 0; i < lineas.length; i++) {
            for (int j = 0; j < lineas[i].length; j++) {
                dibujadas[i][j] = lineas[i][j].estaDibujada();
            }
        }
        return dibujadas;
    }

}
