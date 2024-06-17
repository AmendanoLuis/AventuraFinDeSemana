/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import modelo.Jugador;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.PanelHistoriayDecision;
import vista.VistaActo1;
import lombok.*;
import modelo.Alerta;

@Data
public class ControladorActo1 implements PanelHistoriayDecision {

    // Vista
    private VistaActo1 vistaActo1;

    // Personajes
    private ConversacionesActo1 cConver;
    private int dialogoActual;
    private int imagenHistoriaActual;
    private StackPane cPasarDialogos;

    // Contenedores 
    private Label dialogo;
    private ImageView imagenesHistoria;

    private StackPane contenedorJuego;

    private StackPane cBotonContinuar;

    private ImageView imagenesPersonaje;

    // Contenedores para decisiones
    private StackPane contenedorDialogoYeleccion;
    private VBox cPregunta;
    private HBox contenedorDialogo;

    private boolean dialogoBase = true;
    private boolean cahorros = false;
    private boolean sierraNevada = false;

    //  Alerta
    private Alerta alerta;
    private String instruccionesHistoria = "Haz click para continuar la historia, ve con cuidado, podrias tener elecciones importantes.";

    public ControladorActo1() {
        vistaActo1 = new VistaActo1();

        inicializarComponentes();
        cargarAlerta();

        cargarCampoAccionDialogo();
        mostrarControlHistoria();
    }

    private void mostrarControlHistoria() {
        alerta.mostrarAlerta("Historia", instruccionesHistoria);
    }

    private void inicializarComponentes() {

        cConver = new ConversacionesActo1();
        alerta = new Alerta();

        dialogoActual = 0;
        imagenHistoriaActual = 1;

        cPasarDialogos = vistaActo1.getCPasarDialogos();

        contenedorDialogoYeleccion = vistaActo1.getContenedorDialogoyEleccion();
        cPregunta = vistaActo1.getCPregunta();

        contenedorDialogo = vistaActo1.getContenedorDialogo();

        imagenesPersonaje = vistaActo1.getImagenPersonaje();
        imagenesHistoria = vistaActo1.getImagenesHistoria();

        contenedorJuego = vistaActo1.getContenedorJuego();

        dialogo = vistaActo1.getDialogo();

        cBotonContinuar = vistaActo1.getCContinuar();

    }

    private void cargarAlerta() {
        contenedorJuego.getChildren().add(alerta.getAlerta());
    }

    protected void iniciarEventos() {

        cPasarDialogos.setOnMouseClicked(event -> {

            avanzarDialogo();
        });
        vistaActo1.getBtnCahorros().setOnAction(event -> {
            seleccionarDestino(1);
            avanzarDialogo();
            establecerPanelDialogo();
            cargarCampoAccionDialogo();
            dialogoBase = false;

        });
        vistaActo1.getBtnSierraNevada().setOnAction(event -> {
            seleccionarDestino(2);
            avanzarDialogo();
            establecerPanelDialogo();
            cargarCampoAccionDialogo();
            dialogoBase = false;

        });

    }

    private void cargarCampoAccionDialogo() {
        cPasarDialogos.setVisible(true);
        cPasarDialogos.toFront();
        cPasarDialogos.setCursor(Cursor.HAND);

    }

    private void retirarCampoAccionDialogo() {
        cPasarDialogos.toBack();
        cPasarDialogos.setVisible(false);
    }

    private void avanzarDialogo() {

        mostrarDialogo();
        mostrarPosePersonaje();
        dialogoActual++;

        if (dialogoActual == 2) {
            cambiarImagenHistoria();

        }
        if (dialogoActual == 5) {
            retirarCampoAccionDialogo();
            establecerPanelDecision();

        }
        if (dialogoActual == 7) {
            cambiarImagenHistoria();

        }

        if (dialogoActual == 8) {

            cambiarImagenHistoria();
        }

    }

    private void mostrarDialogo() {
        String dialogoTexto = " ";

        if (dialogoBase) {
            dialogoTexto = cConver.obtenerDialogos();

        }

        if (cahorros) {
            dialogoTexto = cConver.obtenerDialogos();

        }
        if (sierraNevada) {
            dialogoTexto = cConver.obtenerDialogos();

        }

        if (dialogoTexto != null && !dialogoTexto.isEmpty()) {
            dialogo.setText(dialogoTexto);
        } else {

        }

    }

    private void mostrarPosePersonaje() {

        try {
            String imagenPersonaje = cConver.obtenerPoses();
            Image imgPersonaje = new Image(imagenPersonaje);
            if (imgPersonaje != null) {
                imagenesPersonaje.setImage(imgPersonaje);
            } else {
                System.out.println("La pose para la clave p" + imagenHistoriaActual + " es nulo o vacío");
            }
        } catch (NullPointerException e) {
            retirarCampoAccionDialogo();

            System.out.println("Error: " + e);
            cBotonContinuar.setVisible(true);
            cBotonContinuar.toFront();
        }

    }

    @Override
    public void establecerPanelJuego() {

    }

    @Override
    public void establecerPanelDialogo() {

        contenedorDialogoYeleccion.getChildren().clear();
        contenedorDialogoYeleccion.getChildren().add(contenedorDialogo);
    }

    @Override
    public void establecerPanelDecision() {
        cPasarDialogos.setVisible(false);
        contenedorDialogoYeleccion.getChildren().clear();
        contenedorDialogoYeleccion.getChildren().addAll(cPregunta);
    }

    @Override
    public void cambiarImagenHistoria() {
        String claveImagen = "Imagen " + imagenHistoriaActual;
        String pathImagen = vistaActo1.getPathImagen(claveImagen);
        if (pathImagen != null && !pathImagen.isEmpty()) {
            try {
                Image imgHistoria = new Image(pathImagen);
                imagenesHistoria.setImage(imgHistoria);
                imagenHistoriaActual++;
            } catch (IllegalArgumentException e) {
                System.err.println("Error al cargar la imagen de la historia: " + e.getMessage());
            }
        } else {
            System.err.println("El path de la imagen de la historia es nulo o vacío");
        }
    }

    private void seleccionarDestino(int eleccion) {
        Jugador jugador = Jugador.getInstanciaJugador();

        if (eleccion == 1) {
            jugador.seleccionarCahorros();
            this.cahorros = true;
            this.dialogoBase = false;
            cConver.cargarDialogoCahorros();

        }

        if (eleccion == 2) {
            jugador.seleccionarSierraNevada();
            this.sierraNevada = true;
            this.dialogoBase = false;
            cConver.cargarDialogoSierraNevada();

        }

    }

}
