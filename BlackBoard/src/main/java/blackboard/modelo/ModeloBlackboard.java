/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackboard.modelo;

import blackboard.evento.Evento;
import blackboard.ks.KSEvaluarJugadoresListos;
import modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseq
 */
public class ModeloBlackboard extends Observable {

    private final List<Evento> eventos;

    public ModeloBlackboard() {
        eventos = new ArrayList<>();
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
        notificarObservadores(evento);
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Jugador> getJugadores() {
        for (Observador obs : getObservadores()) {
            if (obs instanceof KSEvaluarJugadoresListos ks) {
                return ks.getJugadoresRegistrados();
            }
        }
        return List.of(); // Si no hay KS registrado
    }

}
