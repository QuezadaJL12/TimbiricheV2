package mvcLobby;

import modelo.Jugador;
import com.mycompany.blackboard.Blackboard;
import blackboard.modelo.ModeloBlackboard;
import mvcJuego.ControladorJuego;
import mvcJuego.ModeloJuego;
import mvcJuego.VistaJuego;

import java.util.List;

public class ControladorLobby {
    private final VistaLobby vista;
    private final ModeloLobby modelo;
    private final Jugador jugador;
    private final boolean esHost;
    private final int tamanoTablero;
    private final Blackboard blackboard;
    private ControladorJuego controladorJuego;

    public ControladorLobby(VistaLobby vista, ModeloLobby modelo, Jugador jugador, boolean esHost, int tamanoTablero, Blackboard blackboard) {
        this.vista = vista;
        this.modelo = modelo;
        this.jugador = jugador;
        this.esHost = esHost;
        this.tamanoTablero = tamanoTablero;
        this.blackboard = blackboard;

        iniciarLobby();
    }

    public void iniciarLobby() {
        vista.setControlador(this);
        vista.setJugador(jugador);
        vista.setTamanoTablero(tamanoTablero);
        vista.setHost(esHost);

        List<Jugador> jugadores = modelo.getJugadores();
        jugadores.add(jugador);
        vista.mostrarJugadores(jugadores);

        vista.getBtnIniciarPartida().setEnabled(esHost && jugadores.size() >= 2);
        vista.getBtnIniciarPartida().addActionListener(e -> iniciarPartida());

        System.out.println("[ControladorLobby] Lobby iniciado para: " + jugador.getNombre());
        vista.setVisible(true);
    }

    private void iniciarPartida() {
        List<Jugador> jugadores = modelo.getJugadores();
        if (jugadores.size() < 2) {
            vista.mostrarMensaje("Se necesitan al menos 2 jugadores para iniciar la partida.");
            return;
        }
        VistaJuego vistaJuego = new VistaJuego(tamanoTablero, jugadores);
        ModeloJuego modeloJuego = new ModeloJuego(jugadores, tamanoTablero);
        controladorJuego = new ControladorJuego(vistaJuego, modeloJuego, blackboard);

        vista.dispose();
    }

    public ControladorJuego getControladorJuego() {
        return controladorJuego;
    }
} 
