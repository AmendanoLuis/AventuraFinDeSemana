/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.Alerta;
import modelo.ConversacionesRestauranteSn;
import modelo.ConversacionesSierraNevada;
import modelo.Jugador;
import modelo.PanelHistoriayDecision;
import vista.VistaActo1;
import vista.VistaRestauranteSierraNevada;
import lombok.Data;

/**
 *
 * @author Luis
 */
@Data
public class ControladorRestauranteSierraNevada implements PanelHistoriayDecision {

    //  Jugador
    private static int actividadRealizada = 0;

    // Vista
    private VistaRestauranteSierraNevada vRestauranteSn;

    // Personajes
    private ConversacionesRestauranteSn cConverRestSn;
    private int dialogoActual;
    private int imagenHistoriaActual;
    private StackPane cPasarDialogos;

    // Contenedores 
    private Label dialogo;
    private ImageView imagenesHistoria;

    private StackPane contenedorJuego;

    private StackPane cBotonContinuar;
    private Button btnContinuar;

    private ImageView imagenesPersonaje;

    // Contenedores para decisiones
    private StackPane contenedorDialogoYeleccion;
    private VBox cPregunta;
    private HBox contenedorDialogo;

    private boolean dialogoBase = true;
    private boolean cahorros = false;
    private boolean sierraNevada = false;

    public ControladorRestauranteSierraNevada() {
        vRestauranteSn = new VistaRestauranteSierraNevada();

        inicializarComponentes();

        cargarCampoAccionDialogo();
    }

    private void inicializarComponentes() {

        cConverRestSn = new ConversacionesRestauranteSn();

        dialogoActual = 0;
        imagenHistoriaActual = 1;

        cPasarDialogos = vRestauranteSn.getCPasarDialogos();

        btnContinuar = vRestauranteSn.getBtnContinuar();

        contenedorDialogoYeleccion = vRestauranteSn.getContenedorDialogoyEleccion();
        cPregunta = vRestauranteSn.getCPregunta();

        contenedorDialogo = vRestauranteSn.getContenedorDialogo();

        imagenesPersonaje = vRestauranteSn.getImagenPersonaje();
        imagenesHistoria = vRestauranteSn.getImagenesHistoria();

        contenedorJuego = vRestauranteSn.getContenedorJuego();

        dialogo = vRestauranteSn.getDialogo();

        cBotonContinuar = vRestauranteSn.getCContinuar();

    }

    protected void iniciarEventos() {

        cPasarDialogos.setOnMouseClicked(event -> {

            avanzarDialogo();
        });
        vRestauranteSn.getEleccion1().setOnAction(event -> {
            cambiarFlujoHistoria(1);
            avanzarDialogo();
            establecerPanelDialogo();
            cargarCampoAccionDialogo();
            dialogoBase = false;

        });
        vRestauranteSn.getEleccion2().setOnAction(event -> {
            cambiarFlujoHistoria(2);
            avanzarDialogo();
            establecerPanelDialogo();
            cargarCampoAccionDialogo();
            dialogoBase = false;

        });

    }

    protected static void comprobarActividadRealizada() {
        Jugador jugador = Jugador.getInstanciaJugador();

        int count = jugador.getContadorActividades();
        if (actividadRealizada == 1) {
            count += actividadRealizada;
            actividadRealizada = 2;
        }

        jugador.setContadorActividades(count);

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

        if (dialogoActual == 9) {

            cambiarImagenHistoria();
        }

    }

    private void mostrarDialogo() {
        String dialogoTexto = " ";

        if (this.dialogoBase) {
            dialogoTexto = cConverRestSn.obtenerDialogos();

        }

        if (this.cahorros) {
            dialogoTexto = cConverRestSn.obtenerDialogos();

        }
        if (this.sierraNevada) {
            dialogoTexto = cConverRestSn.obtenerDialogos();

        }

        if (dialogoTexto != null && !dialogoTexto.isEmpty()) {
            this.dialogo.setText(dialogoTexto);
        } else {

        }

    }

    private void mostrarPosePersonaje() {

        try {
            String imagenPersonaje = cConverRestSn.obtenerPoses();
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

            actividadRealizada = 1;

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
        String pathImagen = vRestauranteSn.getPathImagen(claveImagen);
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

    private void cambiarFlujoHistoria(int eleccion) {
        Jugador jugador = Jugador.getInstanciaJugador();

        if (eleccion == 1) {

        }

        if (eleccion == 2) {
            if (cConverRestSn.isPregunta1()) {
                cConverRestSn.cambiarPregunta1AFlujo2();

            }

            if (cConverRestSn.isPregunta2()) {
                cConverRestSn.cambiarPregunta2AFlujo2();

            }

        }

    }
}
