package blackboard.ks;

import blackboard.evento.Evento;
import blackboard.evento.EventoRegistro;
import blackboard.modelo.ModeloBlackboard;
import blackboard.modelo.Observador;
import modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseq
 */
public class KSEvaluarJugadoresListos implements Observador {

    private final ModeloBlackboard modelo;
    private final List<Jugador> jugadores;

    public KSEvaluarJugadoresListos(ModeloBlackboard modelo) {
        this.modelo = modelo;
        this.jugadores = new ArrayList<>();
    }

    @Override
    public void actualizar(Object obj) {
        if (obj instanceof Evento evento && evento.getTipo().equals("registro")) {
            // Cast explícito a EventoRegistro para acceder a getJugador()
            Jugador nuevo = ((EventoRegistro) evento).getJugador();

            boolean yaExiste = jugadores.stream()
                    .anyMatch(j -> j.getNombre().equalsIgnoreCase(nuevo.getNombre()));

            if (!yaExiste && jugadores.size() < 4) {
                jugadores.add(nuevo);
                System.out.println("[KS] Jugador registrado: " + nuevo.getNombre());
            }

            if (jugadores.size() >= 2) {
                System.out.println("[KS] Mínimo de jugadores alcanzado. Se puede iniciar.");
            }
        }
    }

    public List<Jugador> getJugadoresRegistrados() {
        return jugadores;
    }
}
