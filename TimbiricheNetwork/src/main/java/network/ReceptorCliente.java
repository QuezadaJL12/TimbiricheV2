package network;

import blackboard.evento.EventoLinea;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import mvcJuego.ControladorJuego;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceptorCliente extends Thread {

    private final Socket socket;
    private final ControladorJuego controlador;
    private final Gson gson;

    public ReceptorCliente(Socket socket, ControladorJuego controlador) {
        this.socket = socket;
        this.controlador = controlador;
        this.gson = new Gson();
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                System.out.println("[ReceptorCliente] Mensaje recibido: " + linea);
                try {
                    EventoLinea evento = gson.fromJson(linea, EventoLinea.class);
                    controlador.procesarEventoLinea(evento);
                } catch (JsonSyntaxException e) {
                    System.out.println("[ReceptorCliente] JSON mal formado: " + linea);
                }
            }
        } catch (Exception e) {
            System.out.println("[ReceptorCliente] Error al leer del servidor");
            e.printStackTrace();
        }
    }
}
