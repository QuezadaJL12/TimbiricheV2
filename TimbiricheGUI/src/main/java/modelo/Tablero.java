package modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private final int tamano;
    private final Linea[][] lineasHorizontales;
    private final Linea[][] lineasVerticales;
    private final Color[][] coloresCuadros;

    public Tablero(int tamano) {
        this.tamano = tamano;
        this.lineasHorizontales = new Linea[tamano + 1][tamano];
        this.lineasVerticales = new Linea[tamano][tamano + 1];
        this.coloresCuadros = new Color[tamano][tamano];

        // Inicializar líneas horizontales
        for (int i = 0; i < tamano + 1; i++) {
            for (int j = 0; j < tamano; j++) {
                lineasHorizontales[i][j] = new Linea(i, j, true); // true para horizontal
            }
        }

// Inicializar líneas verticales
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano + 1; j++) {
                lineasVerticales[i][j] = new Linea(i, j, false); // false para vertical
            }
        }
    }

    public boolean sePuedeTrazarLinea(int fila, int columna, boolean horizontal) {
        return horizontal
                ? !lineasHorizontales[fila][columna].estaDibujada()
                : !lineasVerticales[fila][columna].estaDibujada();
    }

    public List<Cuadro> trazarLineaYObtenerCuadros(int fila, int columna, boolean horizontal, Jugador jugador) {
        List<Cuadro> posibles = new ArrayList<>();

        if (horizontal) {
            lineasHorizontales[fila][columna].dibujar(jugador);

        } else {
            lineasVerticales[fila][columna].dibujar(jugador);

        }

        if (fila > 0 && lineaArribaCompleta(fila, columna)) {
            Linea arriba = getLineaHorizontal(fila - 1, columna);
            Linea abajo = getLineaHorizontal(fila, columna);
            Linea izquierda = getLineaVertical(fila - 1, columna);
            Linea derecha = getLineaVertical(fila - 1, columna + 1);
            Cuadro cuadro = new Cuadro(arriba, abajo, izquierda, derecha);
            if (cuadro.estaCompleto() && !cuadro.yaAsignado()) {
                cuadro.asignarPropietario(jugador);
                coloresCuadros[fila - 1][columna] = Color.decode(jugador.getColorHex());
                posibles.add(cuadro);
            }
        }
        if (fila < tamano - 1 && lineaAbajoCompleta(fila, columna)) {
            Linea arriba = getLineaHorizontal(fila, columna);
            Linea abajo = getLineaHorizontal(fila + 1, columna);
            Linea izquierda = getLineaVertical(fila, columna);
            Linea derecha = getLineaVertical(fila, columna + 1);
            Cuadro cuadro = new Cuadro(arriba, abajo, izquierda, derecha);
            if (cuadro.estaCompleto() && !cuadro.yaAsignado()) {
                cuadro.asignarPropietario(jugador);
                coloresCuadros[fila][columna] = Color.decode(jugador.getColorHex());
                posibles.add(cuadro);
            }
        }
        if (columna > 0 && lineaIzquierdaCompleta(fila, columna - 1)) {
            Linea arriba = getLineaHorizontal(fila, columna - 1);
            Linea abajo = getLineaHorizontal(fila + 1, columna - 1);
            Linea izquierda = getLineaVertical(fila, columna - 1);
            Linea derecha = getLineaVertical(fila, columna);
            Cuadro cuadro = new Cuadro(arriba, abajo, izquierda, derecha);
            if (cuadro.estaCompleto() && !cuadro.yaAsignado()) {
                cuadro.asignarPropietario(jugador);
                coloresCuadros[fila][columna - 1] = Color.decode(jugador.getColorHex());
                posibles.add(cuadro);
            }
        }
        if (columna < tamano - 1 && lineaDerechaCompleta(fila, columna)) {
            Linea arriba = getLineaHorizontal(fila, columna);
            Linea abajo = getLineaHorizontal(fila + 1, columna);
            Linea izquierda = getLineaVertical(fila, columna);
            Linea derecha = getLineaVertical(fila, columna + 1);
            Cuadro cuadro = new Cuadro(arriba, abajo, izquierda, derecha);
            if (cuadro.estaCompleto() && !cuadro.yaAsignado()) {
                cuadro.asignarPropietario(jugador);
                coloresCuadros[fila][columna] = Color.decode(jugador.getColorHex());
                posibles.add(cuadro);
            }
        }

        return posibles;
    }

    public Linea getLineaHorizontal(int fila, int columna) {
        return lineasHorizontales[fila][columna];
    }

    public Linea getLineaVertical(int fila, int columna) {
        return lineasVerticales[fila][columna];
    }

    public boolean lineaArribaCompleta(int fila, int columna) {
        return lineasHorizontales[fila][columna].estaDibujada()
                && lineasVerticales[fila - 1][columna].estaDibujada()
                && lineasVerticales[fila - 1][columna + 1].estaDibujada();
    }

    public boolean lineaAbajoCompleta(int fila, int columna) {
        return lineasHorizontales[fila + 1][columna].estaDibujada()
                && lineasVerticales[fila][columna].estaDibujada()
                && lineasVerticales[fila][columna + 1].estaDibujada();
    }

    public boolean lineaIzquierdaCompleta(int fila, int columna) {
        return lineasHorizontales[fila][columna].estaDibujada()
                && lineasHorizontales[fila + 1][columna].estaDibujada()
                && lineasVerticales[fila][columna].estaDibujada();
    }

    public boolean lineaDerechaCompleta(int fila, int columna) {
        return lineasHorizontales[fila][columna].estaDibujada()
                && lineasHorizontales[fila + 1][columna].estaDibujada()
                && lineasVerticales[fila][columna + 1].estaDibujada();
    }

    public Color[][] getColoresCuadros() {
        return coloresCuadros;
    }

    public Linea[][] getLineasHorizontales() {
        return lineasHorizontales;
    }

    public Linea[][] getLineasVerticales() {
        return lineasVerticales;
    }

    public boolean estaCompleto() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (coloresCuadros[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
