package modelo;

public class Linea {

    private final int fila;
    private final int columna;
    private final boolean horizontal;
    private boolean dibujada;
    private Jugador jugador;

    public Linea(int fila, int columna, boolean horizontal) {
        this.fila = fila;
        this.columna = columna;
        this.horizontal = horizontal;
        this.dibujada = false;
        this.jugador = null;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean esHorizontal() {
        return horizontal;
    }

    public boolean estaDibujada() {
        return dibujada;
    }

    public void dibujar(Jugador jugador) {
        this.dibujada = true;
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
