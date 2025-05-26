package com.mycompany.blackboard;

import blackboard.evento.Evento;
import blackboard.ks.KSEvaluarJugadoresListos;
import blackboard.modelo.ModeloBlackboard;

public class Blackboard {

    private final ModeloBlackboard modelo;

    public Blackboard() {
        modelo = new ModeloBlackboard();
        registrarKnowledgeSources();
    }

    private void registrarKnowledgeSources() {
        modelo.agregarObservador(new KSEvaluarJugadoresListos(modelo));
    }

    public void agregarEvento(Evento evento) {
        modelo.agregarEvento(evento);
    }

    public ModeloBlackboard getModelo() {
        return modelo;
    }
}
