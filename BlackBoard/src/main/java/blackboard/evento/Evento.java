package blackboard.evento;

import modelo.Jugador;
import java.io.Serializable;

public abstract class Evento implements Serializable {

    private final String tipo;
    private final Jugador jugador;

    public Evento(String tipo, Jugador jugador) {
        this.tipo = tipo;
        this.jugador = jugador;
    }

    public String getTipo() {
        return tipo;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
