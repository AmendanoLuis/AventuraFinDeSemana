/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import vista.VistaElegirNombre;
import lombok.*;
import modelo.Alerta;
import modelo.Jugador;

/**
 *
 * @author Luis
 */
@Data
public class ControladorElegirNombre {

    private VistaElegirNombre vistaElegirNombre;

    private Button btnContinuarJuego;
    private Button aceptarNombre;

    private TextField nJugador;
    private String nombreJugador;

    private Alerta alerta;
    private boolean respuesta = false;
    private boolean nEscrito = false;

    private StackPane contenedorJuego;

    public ControladorElegirNombre() {
        this.vistaElegirNombre = new VistaElegirNombre();

        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes() {

        this.alerta = new Alerta();

        contenedorJuego = vistaElegirNombre.getContenedorJuego();
        contenedorJuego.getChildren().add(alerta.getAlerta());

        btnContinuarJuego = vistaElegirNombre.getBtnContinuarJuego();
        aceptarNombre = vistaElegirNombre.getBtnAceptarNombre();

        nJugador = vistaElegirNombre.getNombreField();
    }

    public void iniciarEventos() {

        aceptarNombre.setOnMouseClicked(event -> {
            comprobarNombreEscrito();
            if (nEscrito) {
                eventoAceptarNombreEscrito();
                alerta.mostrarAlerta("Nombre guardado exitosamente", "Bienvenido " + nombreJugador);
            }

        });

        btnContinuarJuego.setOnAction(event -> comprobarNombreJugador());

    }

    private boolean comprobarNombreEscrito(){
          String nombreJugador = nJugador.getText();

        if (nombreJugador != null && nombreJugador != "") {
            nEscrito = true;
        } else {
            nEscrito = false;

            alerta.mostrarAlerta("No sabemos quien eres", "Escribe tu nombre anda, para saber quien eres");
        }
        return nEscrito;
    }
    
    public boolean comprobarNombreJugador() {

        Jugador jugador = Jugador.getInstanciaJugador();
        String nJugador = jugador.getNombre();

        if (nJugador != null && nJugador != "") {
            respuesta = true;
        } else {
            respuesta = false;

            alerta.mostrarAlerta("No sabemos quien eres", "Escribe tu nombre anda, para saber quien eres");
        }
        return respuesta;
    }

    private void eventoAceptarNombreEscrito() {

        Jugador jugador = Jugador.getInstanciaJugador();
        nombreJugador = nJugador.getText();
        jugador.setNombre(nombreJugador);

    }

}
