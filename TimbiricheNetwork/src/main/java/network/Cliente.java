package network;

import blackboard.evento.Evento;
import blackboard.evento.EventoLinea;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import mvcJuego.ControladorJuego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread {

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private Gson gson;
    private ControladorJuego controladorJuego;

    public Cliente(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            gson = new Gson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviar(Evento evento) {
        String json = gson.toJson(evento);
        salida.println(json);
    }

    public void setControladorJuego(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
    }

    @Override
    public void run() {
        String mensaje;
        try {
            while ((mensaje = entrada.readLine()) != null) {
                try {
                    EventoLinea evento = gson.fromJson(mensaje, EventoLinea.class);
                    if (controladorJuego != null) {
                        controladorJuego.procesarEventoLinea(evento);
                    }
                } catch (JsonSyntaxException e) {
                    System.err.println("Error al procesar evento: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Conexión finalizada: " + e.getMessage());
        }
    }
} 
