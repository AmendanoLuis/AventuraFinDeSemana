/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.animation.RotateTransition;
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
    private RotateTransition rotateRueda1;
    private RotateTransition rotateRueda2;

    public TransicionMinijuego(Duration duration, Node coche, Node rueda1, Node rueda2, double inicioX, double finalX, ControladorMinijuego controlador) {
        setCycleDuration(duration);
        this.coche = coche;
        this.inicioX = inicioX;
        this.finalX = finalX;
        this.controlador = controlador;

        cargarRotacionRuedas(rueda1, rueda2, duration);

    }

    private void cargarRotacionRuedas(Node rueda1, Node rueda2, Duration d) {
        rotateRueda1 = new RotateTransition();
        rotateRueda1.setNode(rueda1);
        rotateRueda1.setDuration(d);
        rotateRueda1.setByAngle(360);
        rotateRueda1.setAutoReverse(false);

        rotateRueda2 = new RotateTransition();
        rotateRueda2.setNode(rueda2);
        rotateRueda2.setDuration(d);
        rotateRueda2.setByAngle(360);
        rotateRueda2.setAutoReverse(false);

        rotateRueda1.play();
        rotateRueda2.play();
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
