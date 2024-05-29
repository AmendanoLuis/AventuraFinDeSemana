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
    private HellerKratos hk;
    private Maminha maminha;
    private int dialogoActual;
    private Personaje personajeActual;

    // Contenedores 
    private Label dialogo;
    private ImageView imagenesHistoria;
    private int imagenActual = 1;
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
        hk = new HellerKratos();
        maminha = new Maminha();
        personajeActual = hk;
        dialogoActual = 1;

        // Configura botones y contenedores para decisiones
        eleccion1 = new Button("Elección 1");
        eleccion2 = new Button("Elección 2");
        preguntaEleccion = new Label("¿Qué decisión tomarás?");
        contenedorBotonEleccion = new HBox(eleccion1, eleccion2);
        contenedorDialogo = new HBox();
        imagenesPersonaje = new ImageView();

        // Configura la primera imagen de la historia
        imagenesHistoria = vistaActo1.getImagenesHistoria();

        // Configura el contenedor de juego
        contenedorJuego = vistaActo1.getContenedorJuego();

        // Configura el diálogo
        dialogo = vistaActo1.getDialogo();
        mostrarDialogo();

        // Configura la imagen del personaje con una imagen por defecto
        establecerImagenPersonaje();
    }

    private void iniciarEventos() {
        contenedorJuego.setOnMouseClicked(event -> avanzarDialogo());
        eleccion1.setOnAction(event -> manejarEleccion(1));
        eleccion2.setOnAction(event -> manejarEleccion(2));
    }

    private void avanzarDialogo() {
        // Suponiendo que cada personaje tiene 5 diálogos
        if (dialogoActual <= 5) {  
            mostrarDialogo();
            dialogoActual++;
        } else {
            cambiarPersonajeMaminha();
            dialogoActual = 1;
            mostrarDialogo();
        }

        if (personajeActual instanceof Maminha && dialogoActual == 1) {
            cambiarImagenHistoria();
            establecerImagenPersonaje();
        }
    }

    private void mostrarDialogo() {
        String dialogoTexto = personajeActual.obtenerDialogo("d" + dialogoActual);
        if (dialogoTexto != null && !dialogoTexto.isEmpty()) {
            dialogo.setText(dialogoTexto);
        } else {
            System.out.println("El diálogo para la clave d" + dialogoActual + " es nulo o vacío");
        }
    }

    private void cambiarPersonajeMaminha() {
        if (personajeActual instanceof HellerKratos) {
            personajeActual = maminha;
        } else {
            personajeActual = hk;
        }
        establecerImagenPersonaje();
    }

    private void establecerImagenPersonaje() {
        // Se elimina la imagen anterior del contenedor
        contenedorDialogo.getChildren().remove(imagenesPersonaje);
        // Se obtiene la imagen del personaje actual
        String imagenPersonaje = personajeActual.obtenerPose("p1");
        Image imgPersonaje = new Image(imagenPersonaje);

        if (imgPersonaje != null) {
            imagenesPersonaje = new ImageView(imgPersonaje);

// Se agrega la nueva imagen al contenedor
            contenedorDialogo.getChildren().add(imagenesPersonaje);
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
        imagenActual++;
        String claveImagen = "Imagen " + imagenActual;
        String pathImagen = vistaActo1.getPathImagen(claveImagen);
        if (pathImagen != null && !pathImagen.isEmpty()) {
            try {
                Image imgHistoria = new Image(pathImagen);
                imagenesHistoria.setImage(imgHistoria);
                imagenActual++;
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
