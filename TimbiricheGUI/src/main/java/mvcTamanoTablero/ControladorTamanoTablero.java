package mvcTamanoTablero;

import modelo.Jugador;
import com.mycompany.blackboard.Blackboard;
import mvcLobby.VistaLobby;
import mvcLobby.ControladorLobby;
import mvcLobby.ModeloLobby;
import network.Cliente;
import network.Servidor;
import mvcJuego.ControladorJuego;
import mvcJuego.ModeloJuego;
import mvcJuego.VistaJuego;

public class ControladorTamanoTablero {
    private final VistaTamanoTablero vista;
    private final ModeloTamanoTablero modelo;
    private final Jugador jugador;
    private final Blackboard blackboard;

    public ControladorTamanoTablero(VistaTamanoTablero vista, ModeloTamanoTablero modelo, Jugador jugador, Blackboard blackboard) {
        this.vista = vista;
        this.modelo = modelo;
        this.jugador = jugador;
        this.blackboard = blackboard;

        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.getBtnTam10().addActionListener(e -> seleccionarTamano(10));
        vista.getBtnTam20().addActionListener(e -> seleccionarTamano(20));
        vista.getBtnTam30().addActionListener(e -> seleccionarTamano(30));

        vista.getBtnServidor().addActionListener(e -> crearServidor());
        vista.getBtnUnirse().addActionListener(e -> unirseAServidor());
    }

    private void seleccionarTamano(int tamano) {
        modelo.setTamanoTablero(tamano);
        vista.mostrarMensaje("Tamaño seleccionado: " + tamano + "x" + tamano);
    }

    private void crearServidor() {
        int tamano = modelo.getTamanoTablero();
        if (tamano == 0) {
            vista.mostrarMensaje("Por favor selecciona un tamaño de tablero antes de continuar.");
            return;
        }

        Servidor servidor = new Servidor(5000);
        servidor.start();

        ModeloLobby modeloLobby = new ModeloLobby();
        VistaLobby vistaLobby = new VistaLobby(jugador, tamano, true, blackboard);
        ControladorLobby controladorLobby = new ControladorLobby(vistaLobby, modeloLobby, jugador, true, tamano, blackboard);
        servidor.setControladorJuego(controladorLobby.getControladorJuego());

        controladorLobby.iniciarLobby();
        vista.dispose();
    }

    private void unirseAServidor() {
        int tamano = modelo.getTamanoTablero();
        if (tamano == 0) {
            vista.mostrarMensaje("Selecciona un tamaño de tablero antes de conectarte.");
            return;
        }

        Cliente cliente = new Cliente("localhost", 5000);
        cliente.start();

        ModeloLobby modeloLobby = new ModeloLobby();
        VistaLobby vistaLobby = new VistaLobby(jugador, tamano, false, blackboard);
        ControladorLobby controladorLobby = new ControladorLobby(vistaLobby, modeloLobby, jugador, false, tamano, blackboard);
        cliente.setControladorJuego(controladorLobby.getControladorJuego());
        controladorLobby.getControladorJuego().setCliente(cliente);

        controladorLobby.iniciarLobby();
        vista.dispose();
    }
} 
