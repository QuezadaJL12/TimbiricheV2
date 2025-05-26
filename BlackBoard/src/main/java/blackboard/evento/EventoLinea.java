package blackboard.evento;

import modelo.Jugador;
import java.io.Serializable;

public class EventoLinea extends Evento implements Serializable {

    private final int fila;
    private final int columna;
    private final boolean horizontal;

    public EventoLinea(Jugador jugador, int fila, int columna, boolean horizontal) {
        super("linea", jugador);
        this.fila = fila;
        this.columna = columna;
        this.horizontal = horizontal;
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
}
