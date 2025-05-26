
import network.Servidor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joseq
 */
public class Prueba {
     public static void main(String[] args) {
        int puerto = 5000;
        Servidor servidor = new Servidor(puerto);
        servidor.run();
        
    }
}
