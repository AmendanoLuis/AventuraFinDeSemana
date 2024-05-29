/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.List;
import javafx.scene.control.Alert;
import modelo.Items;
import modelo.Jugador;

/**
 *
 * @author Luis
 */
public class ControladorJugador {

    private Jugador jugador;

    public ControladorJugador() {
        this.jugador = Jugador.getInstanciaJugador();
    }

    public void setNombre(String nombre) {
        jugador.setNombre(nombre);
    }

    public void setDinero(double dinero) {
jugador.setDinero(dinero);
    }
    
    public void agregarItemJugador(Items item) {
        jugador.añadirItem(item);
    }

    public String getNombre() {
        return jugador.getNombre();
    }

    public double getDinero() {
        return jugador.getDinero();
    }
    
    public void agregarDinero(double cantidad) {
        double jDinero = jugador.getDinero();
        if (cantidad > 0) {
            jDinero += cantidad;

        } else {
            showInformationAlert("No se puede agregar una cantidad negativa.");
        }
    }

    public void quitarDinero(double cantidad) {
        double jDinero = jugador.getDinero();

        if (cantidad > 0) {
            if (jDinero - cantidad >= 0) {
                jDinero -= cantidad;
                jugador.setDinero(jDinero);
            } else {
                showInformationAlert("No tienes suficiente dinero.");

            }
        } else {
            showInformationAlert("No se puede quitar una cantidad negativa.");

        }
    }

    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
