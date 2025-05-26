package mvcRegistro;

import blackboard.evento.EventoRegistro;
import com.mycompany.blackboard.Blackboard;
import modelo.Jugador;
import network.Cliente;

public class ControlRegistro {

    private final VistaRegistro vista;
    private final ModeloRegistro modelo;
    private final Blackboard blackboard;
    private final Cliente cliente;

    public ControlRegistro(VistaRegistro vista, ModeloRegistro modelo, Blackboard blackboard, Cliente cliente) {
        this.vista = vista;
        this.modelo = modelo;
        this.blackboard = blackboard;
        this.cliente = cliente;

        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.getBtnRegistrarse().addActionListener(e -> registrarJugador());
    }

    private void registrarJugador() {
        String nombre = vista.getNombreJugador();
        String rutaAvatar = vista.getRutaAvatarSeleccionado();

        if (nombre.isEmpty() || rutaAvatar == null) {
            vista.mostrarMensaje("Por favor, ingresa un nombre y selecciona un avatar.");
            return;
        }

        modelo.setNombre(nombre);
        modelo.setAvatar(rutaAvatar);

        Jugador jugador = new Jugador(nombre, "#FF0000", rutaAvatar, false, 0);
        EventoRegistro evento = new EventoRegistro(jugador);

        if (cliente != null) {
            cliente.enviar(evento);
        } else {
            blackboard.agregarEvento(evento);
        }

        vista.mostrarMensaje("\u00a1Jugador registrado exitosamente!");
        vista.dispose();
    }
}
