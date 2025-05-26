package network;

import java.io.PrintWriter;
import java.util.function.Consumer;

public class ManejadorCliente {

    private final PrintWriter salida;

    public ManejadorCliente(PrintWriter salida) {
        this.salida = salida;
    }

    public void enviar(String mensaje) {
        salida.println(mensaje);
    }
}
