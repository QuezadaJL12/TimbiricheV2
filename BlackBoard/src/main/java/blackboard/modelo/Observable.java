package blackboard.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private final List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    protected List<Observador> getObservadores() {
        return observadores;
    }

    protected void notificarObservadores(Object evento) {
        for (Observador o : observadores) {
            o.actualizar(evento);
        }
    }
}
