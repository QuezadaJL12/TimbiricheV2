package blackboard.evento;

import modelo.Jugador;

public class EventoRegistro extends Evento {

    public EventoRegistro(Jugador jugador) {
        super("registro", jugador); 
    }

    @Override
    public Jugador getJugador() {
        return super.getJugador(); 
    }
}
