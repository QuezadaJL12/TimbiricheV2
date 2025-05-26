package modelo;

public class Cuadro {

    private final Linea arriba;
    private final Linea abajo;
    private final Linea izquierda;
    private final Linea derecha;
    private Jugador propietario;

    public Cuadro(Linea arriba, Linea abajo, Linea izquierda, Linea derecha) {
        this.arriba = arriba;
        this.abajo = abajo;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.propietario = null;
    }

    public boolean estaCompleto() {
        return arriba.estaDibujada() && abajo.estaDibujada()
                && izquierda.estaDibujada() && derecha.estaDibujada();
    }

    public void asignarPropietario(Jugador jugador) {
        this.propietario = jugador;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public boolean yaAsignado() {
        return propietario != null;
    }
}
