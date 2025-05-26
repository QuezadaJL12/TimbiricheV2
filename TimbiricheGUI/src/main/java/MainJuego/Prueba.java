/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainJuego;

import com.mycompany.blackboard.Blackboard;
import mvcRegistro.ControlRegistro;
import mvcRegistro.ModeloRegistro;
import mvcRegistro.VistaRegistro;
import network.Cliente;

/**
 *
 * @author joseq
 */
public class Prueba {
    //fafafaf
    public static void main(String[] args) {
        Blackboard blackboard = new Blackboard();
        
        VistaRegistro vista = new VistaRegistro();
        ModeloRegistro modelo = new ModeloRegistro();
        // Crear cliente TCP y conectar al servidor
        Cliente cliente = new Cliente("localhost", 5000);
        cliente.start();
        // Iniciar flujo de registro
        new ControlRegistro(vista, modelo, blackboard, cliente);
        
    }
}
