/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Luis
 */
public class TransicionMinijuego extends Transition {

    private Node coche;
    private double inicioX;
    private double finalX;
    private double posicionActualCoche;
    private ControladorMinijuego controlador;


    public TransicionMinijuego(Duration duration, Node coche, double inicioX, double finalX, ControladorMinijuego controlador) {
        setCycleDuration(duration);
        this.coche = coche;
        this.inicioX = inicioX;
        this.finalX = finalX;
        this.controlador = controlador; 
    }

    @Override
    protected void interpolate(double frac) {
        // Calcula la nueva posición X del nodo en función del progreso de la transición
        double newX = inicioX + frac * (finalX - inicioX);
        // Establece la nueva posición X del nodo
        coche.setTranslateX(newX);

        // Actualiza la posición actual del nodo
        posicionActualCoche = newX;

        if (frac < 1.0) {
            controlador.mostrarAviso(posicionActualCoche);

        }
    }

    public double getCurrentPosition() {
        return posicionActualCoche;
    }
}
