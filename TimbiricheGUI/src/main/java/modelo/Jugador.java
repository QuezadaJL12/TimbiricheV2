/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.ImageIcon;
import java.io.Serializable;

/**
 *
 * @author joseq
 */
public class Jugador implements Serializable {

    private String nombre;
    private String colorHex;
    private String rutaAvatar;
    private boolean listo;
    private int puntos;

    public Jugador(String nombre, String colorHex, String rutaAvatar, boolean listo, int puntos) {
        this.nombre = nombre;
        this.colorHex = colorHex;
        this.rutaAvatar = rutaAvatar;
        this.listo = listo;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getRutaAvatar() {
        return rutaAvatar;
    }

    public boolean isListo() {
        return listo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public void setRutaAvatar(String rutaAvatar) {
        this.rutaAvatar = rutaAvatar;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public ImageIcon getAvatarIcon() {
        return new ImageIcon(rutaAvatar);
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
