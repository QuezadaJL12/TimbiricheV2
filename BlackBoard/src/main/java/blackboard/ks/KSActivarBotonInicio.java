/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackboard.ks;

import blackboard.evento.Evento;
import blackboard.modelo.ModeloBlackboard;
import blackboard.modelo.Observador;
import modelo.Jugador;
import java.util.List;


public class KSActivarBotonInicio implements Observador {

    private final ModeloBlackboard modelo;

    public KSActivarBotonInicio(ModeloBlackboard modelo) {
        this.modelo = modelo;
    }

    @Override
    public void actualizar(Object obj) {
        if (obj instanceof Evento evento && evento.getTipo().equals("jugador_listo")) {
            List<Jugador> jugadores = modelo.getJugadores();

            long listos = jugadores.stream().filter(Jugador::isListo).count();

            if (listos >= 2 && listos <= 4) {
                System.out.println("[KS] Jugadores listos suficientes. Activar botón de inicio.");
                // Aquí se podría disparar un nuevo evento si se requiere notificar a la vista
            } else {
                System.out.println("[KS] Jugadores listos insuficientes para iniciar.");
            }
        }
    }
}
