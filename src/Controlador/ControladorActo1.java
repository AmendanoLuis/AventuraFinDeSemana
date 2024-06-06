/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import modelo.Jugador;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.PanelHistoriayDecision;
import modelo.Personaje;
import personajes.HellerKratos;
import personajes.Maminha;
import vista.VistaActo1;

public class ControladorActo1 implements PanelHistoriayDecision {

    // Vista
    private VistaActo1 vistaActo1;
    private ControladorJugador cJugador;

    // Personajes
    private ConversacionesYPoses cConver;
    private int dialogoActual;
    private int poseActual;
    private int imagenHistoriaActual;

    // Contenedores 
    private Label dialogo;
    private ImageView imagenesHistoria;
    private VBox contenedorJuego;

    private ImageView imagenesPersonaje;

    // Contenedores para decisiones
    private Button eleccion1;
    private Button eleccion2;
    private Label preguntaEleccion;
    private HBox contenedorBotonEleccion;
    private HBox contenedorDialogo;

    public ControladorActo1() {
        vistaActo1 = new VistaActo1();
        inicializarComponentes();
        iniciarEventos();
    }

    private void inicializarComponentes() {

        cConver = new ConversacionesYPoses();
        dialogoActual = 1;
        poseActual = 1;
        imagenHistoriaActual = 1;

        // Configura botones y contenedores para decisiones
        eleccion1 = new Button("Elección 1");
        eleccion2 = new Button("Elección 2");
        preguntaEleccion = new Label("¿Qué decisión tomarás?");
        contenedorBotonEleccion = new HBox(eleccion1, eleccion2);
        contenedorDialogo = new HBox();
        imagenesPersonaje = new ImageView();

        imagenesHistoria = vistaActo1.getImagenesHistoria();

        contenedorJuego = vistaActo1.getContenedorJuego();

        dialogo = vistaActo1.getDialogo();
        mostrarDialogo();

        establecerPosePersonaje();
    }

    private void iniciarEventos() {

        imagenesHistoria.setOnMouseClicked(event -> avanzarDialogo());
        eleccion1.setOnAction(event -> manejarEleccion(1));
        eleccion2.setOnAction(event -> manejarEleccion(2));

    }

    private void avanzarDialogo() {
        mostrarDialogo();
        dialogoActual++;

        //  Cambiamos Imagen de Historia cuando lleguemos a x momento
        if (dialogoActual == 2) {
            cambiarImagenHistoria();

        }
        if (dialogoActual == 5) {
            cambiarImagenHistoria();

        }
        if (dialogoActual == 7) {
            cambiarImagenHistoria();
        }

        if (dialogoActual > 9) {
            vistaActo1.getBtnContinuar().setVisible(true);
        }

    }

    private void mostrarDialogo() {
        String dialogoTexto = cConver.obtenerDialogoAc1(dialogoActual);
        if (dialogoTexto != null && !dialogoTexto.isEmpty()) {
            dialogo.setText(dialogoTexto);
        } else {
            System.out.println("El diálogo para la clave d" + dialogoActual + " es nulo o vacío");
        }
    }

    private void establecerPosePersonaje() {
        contenedorDialogo.getChildren().remove(imagenesPersonaje);

        //  Cambiar origen de string
        String imagenPersonaje = cConver.obtenerPosePersonajeAc1(poseActual);
        Image imgPersonaje = new Image(imagenPersonaje);
        poseActual++;
        if (imgPersonaje != null) {
            imagenesPersonaje = new ImageView(imgPersonaje);
            contenedorDialogo.getChildren().add(imagenesPersonaje);
        } else {
            System.out.println("La pose para la clave p" + imagenHistoriaActual + " es nulo o vacío");
        }
    }

    @Override
    public void establecerPanelJuego() {
        // Limpiamos contenedor del juego antes de establecer nuestro panel
        contenedorJuego.getChildren().clear();
        // Establecemos primero el dialogo
        contenedorJuego.getChildren().addAll(imagenesHistoria, dialogo);
    }

    @Override
    public void establecerPanelDialogo() {
        contenedorDialogo.getChildren().clear();
        contenedorDialogo.getChildren().addAll(imagenesPersonaje, dialogo);
        contenedorJuego.getChildren().clear();
        contenedorJuego.getChildren().add(contenedorDialogo);
    }

    @Override
    public void establecerPanelDecision() {
        contenedorBotonEleccion.getChildren().clear();
        contenedorJuego.getChildren().clear();
        contenedorJuego.getChildren().addAll(contenedorDialogo, preguntaEleccion, contenedorBotonEleccion);
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

    private void manejarEleccion(int eleccion) {
        // Aquí se manejarán las elecciones del jugador
        showInformationAlert("Has seleccionado la elección " + eleccion);
        // Agregar lógica de decisión aquí
    }

    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public VistaActo1 getVistaActo1() {
        return vistaActo1;
    }

    public VBox getContenedorJuego() {
        return contenedorJuego;
    }

}
