package network;

import blackboard.evento.EventoLinea;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import mvcJuego.ControladorJuego;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor extends Thread {

    private final int puerto;
    private final Gson gson;
    private final List<PrintWriter> clientes;
    private ControladorJuego controladorJuego;

    public Servidor(int puerto) {
        this.puerto = puerto;
        this.gson = new Gson();
        this.clientes = new ArrayList<>();
    }

    public void setControladorJuego(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
    }

    @Override
    public void run() {
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("[Servidor] Esperando conexiones en el puerto " + puerto + "...");
            while (true) {
                Socket clienteSocket = servidor.accept();
                System.out.println("[Servidor] Cliente conectado desde " + clienteSocket.getInetAddress());

                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);
                clientes.add(salida);

                new Thread(() -> escucharCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escucharCliente(Socket socket) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                System.out.println("[Servidor] Mensaje recibido: " + linea);
                try {
                    EventoLinea evento = gson.fromJson(linea, EventoLinea.class);
                    if (controladorJuego != null) {
                        controladorJuego.procesarEventoLinea(evento);
                        enviarATodos(linea);
                    }
                } catch (JsonSyntaxException e) {
                    System.out.println("[Servidor] JSON mal formado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarATodos(String mensaje) {
        for (PrintWriter cliente : clientes) {
            cliente.println(mensaje);
        }
    }

    public ControladorJuego getControladorJuego() {
        return controladorJuego;
    }
}
