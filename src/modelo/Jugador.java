/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.*;

/**
 *
 * @author Luis
 */
@Data
public class Jugador {

    private static Jugador instanciaJugador;

    private String nombre;
    private double dinero = 7.50;

    private boolean sierraNevada = false;
    private boolean cahorros = false;

    private Image imgDineroJugador;
    private ImageView iconoDineroJugador;
    private List<Items> items;

    private Jugador() {
        imgDineroJugador = new Image("resources/iconoDinero.png");
        iconoDineroJugador = new ImageView(imgDineroJugador);
        
        iconoDineroJugador.setFitHeight(20);
        iconoDineroJugador.setFitWidth(20);
        
        
        items = new ArrayList();
        //  Altura
        iconoDineroJugador.setFitHeight(40);
        //  Ancho
        iconoDineroJugador.setFitWidth(40);

    }

    public static Jugador getInstanciaJugador() {

        if (instanciaJugador == null) {
            instanciaJugador = new Jugador();
        }

        return instanciaJugador;

    }

    public void añadirItem(Items i) {
        items.add(i);
    }

    public void agregarDinero(double cantidad) {
        double jDinero = dinero;
        if (cantidad > 0) {
            jDinero += cantidad;
            dinero = jDinero;
        } else {
            showInformationAlert("No se puede agregar una cantidad negativa.");
        }
    }

    public void quitarDinero(double cantidad) {
        double jDinero = dinero;

        if (cantidad > 0) {
            if (jDinero - cantidad >= 0) {
                jDinero -= cantidad;
                dinero = jDinero;

            } else {
                showInformationAlert("No tienes suficiente dinero.");

            }
        } else {
            showInformationAlert("No se puede quitar una cantidad negativa.");

        }
    }

    public ImageView getIconoDineroJugador() {
        return this.iconoDineroJugador;
    }

    public List<Items> getItems() {
        return items;
    }

    public void seleccionarCahorros() {
        this.cahorros = true;
    }

    public void seleccionarSierraNevada() {
        this.sierraNevada = true;

    }

    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
