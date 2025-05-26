package mvcJuego;

import blackboard.evento.EventoLinea;
import com.mycompany.blackboard.Blackboard;
import modelo.Jugador;
import network.Cliente;

import java.awt.Color;
import java.util.List;

public class ControladorJuego {

    private final VistaJuego vista;
    private final ModeloJuego modelo;
    private final Blackboard blackboard;
    private Cliente cliente;

    public ControladorJuego(VistaJuego vista, ModeloJuego modelo, Blackboard blackboard) {
        this.vista = vista;
        this.modelo = modelo;
        this.blackboard = blackboard;

        vista.setClickListener(this::manejarClick);
        vista.actualizarPuntajes(modelo.getPuntajes());
        vista.actualizarTablero(
                modelo.getLineasHorizontalesDibujadas(),
                modelo.getLineasVerticalesDibujadas(),
                modelo.getColoresCuadros()
        );
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void manejarClick(int fila, int columna, boolean horizontal) {
        Jugador jugador = modelo.getJugadorActual();

        if (!modelo.sePuedeTrazarLinea(fila, columna, horizontal)) {
            vista.mostrarMensaje("Esa línea ya ha sido trazada.");
            return;
        }

        vista.pintarLinea(fila, columna, horizontal, Color.decode(jugador.getColorHex()));

        EventoLinea evento = new EventoLinea(jugador, fila, columna, horizontal);

        if (blackboard != null) {
            blackboard.agregarEvento(evento);
        } else if (cliente != null) {
            cliente.enviar(evento);
        }

        boolean cuadroFormado = modelo.seFormoCuadro(fila, columna, horizontal, jugador);

        vista.actualizarPuntajes(modelo.getPuntajes());
        vista.actualizarTablero(
                modelo.getLineasHorizontalesDibujadas(),
                modelo.getLineasVerticalesDibujadas(),
                modelo.getColoresCuadros()
        );

        if (!cuadroFormado) {
            modelo.siguienteTurno();
        }

        if (modelo.juegoFinalizado()) {
            mostrarPantallaFinal();
        }
    }

    public void procesarEventoLinea(EventoLinea e) {
        vista.pintarLinea(
                e.getFila(),
                e.getColumna(),
                e.esHorizontal(),
                Color.decode(e.getJugador().getColorHex())
        );
        modelo.seFormoCuadro(e.getFila(), e.getColumna(), e.esHorizontal(), e.getJugador());

        vista.actualizarPuntajes(modelo.getPuntajes());
        vista.actualizarTablero(
                modelo.getLineasHorizontalesDibujadas(),
                modelo.getLineasVerticalesDibujadas(),
                modelo.getColoresCuadros()
        );
    }

    private void mostrarPantallaFinal() {
        List<Jugador> jugadores = modelo.getJugadores();
        VistaPuntuacionFinal puntuacionFinal = new VistaPuntuacionFinal(jugadores);
        puntuacionFinal.setVisible(true);
        vista.dispose();
    }
}
