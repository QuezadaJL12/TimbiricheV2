package mvcLobby;

import modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que gestiona el estado del lobby: jugadores conectados, si es host y
 * el tamaño del tablero.
 */
public class ModeloLobby {

    private final List<Jugador> jugadores;
    private int tamanoTablero;
    private boolean esHost;

    public ModeloLobby() {
        jugadores = new ArrayList<>();
        tamanoTablero = 0; // No seleccionado por defecto
        esHost = false;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setTamanoTablero(int tamano) {
        this.tamanoTablero = tamano;
    }

    public int getTamanoTablero() {
        return tamanoTablero;
    }

    public boolean isHost() {
        return esHost;
    }

    public void setHost(boolean esHost) {
        this.esHost = esHost;
    }

    public void actualizarJugador(Jugador actualizado) {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            if (j.getNombre().equals(actualizado.getNombre())) {
                jugadores.set(i, actualizado);
                break;
            }
        }
    }

    public boolean nombreRepetido(String nombre) {
        return jugadores.stream()
                .anyMatch(j -> j.getNombre().equalsIgnoreCase(nombre));
    }

    public boolean avatarRepetido(String rutaAvatar) {
        return jugadores.stream()
                .anyMatch(j -> j.getRutaAvatar().equals(rutaAvatar));
    }
}
