/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Jugador;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VistaActo1 {

    private String nJugador;
    private Jugador jugador;

    private Label nombreJugador;
    private ImageView iconoDineroJugador;
    private String dJugador;

    private Label dineroJugador;
    private HBox contenedorStatsJugador;

    private VBox contenedorJuego;
    private Map<String, String> pathImgHistoria;
    private Image imgHistoria;
    private ImageView imagenesHistoria;

    private Label dialogo;
    private StackPane contImgPersonaje;
    private Image imgPersonaje;
    private ImageView imagenPersonaje;
    private Button btnContinuar;

    private Button eleccion1;
    private Button eleccion2;
    private Label preguntaEleccion;
    private HBox contenedorBotonEleccion;
    private HBox contenedorDialogo;

    public VistaActo1() {
        inicializarComponentes();

    }

    private void inicializarComponentes() {

        jugador = Jugador.getInstanciaJugador();

        pathImgHistoria = new HashMap<>();

        dialogo = new Label();

        contImgPersonaje = new StackPane();

        contenedorStatsJugador = new HBox(10);

        contenedorJuego = new VBox(20);

        contenedorDialogo = new HBox(20);

        contenedorBotonEleccion = new HBox();

        actualizarNombreJugador();

        actualizarDineroJugador();

        cargarStatsJugador();

        cargarImagenesHistoria();

        cargarContenedorDialogo();

        cargarContenedorEleccion();

        contenedorJuego.setPadding(new Insets(-20, 10, 10, 10));
        contenedorJuego.setAlignment(Pos.CENTER);
        contenedorJuego.getChildren().addAll(contenedorStatsJugador, imagenesHistoria, contenedorDialogo);
    }

    private void cargarContenedorEleccion() {
        eleccion1 = new Button("Sierra Nevada");
        eleccion2 = new Button("Cahorros");
        preguntaEleccion = new Label("¿Dónde quieres viajar?");
        contenedorBotonEleccion.setAlignment(Pos.CENTER);
        contenedorBotonEleccion.getChildren().addAll(eleccion1, eleccion2);
    }

    private void cargarContenedorDialogo() {
        dialogo.setMaxWidth(600);
        dialogo.setWrapText(true);

        dialogo.setFont(Font.font(22));
        dialogo.setTextFill(Color.WHITE);

        contenedorDialogo.setAlignment(Pos.CENTER_LEFT);

        imgPersonaje = null;
        imagenPersonaje = new ImageView(imgPersonaje);

        imagenPersonaje.setFitWidth(70);
        imagenPersonaje.setFitHeight(130);
        imagenPersonaje.toFront();

        contImgPersonaje.getChildren().add(imagenPersonaje);
        contImgPersonaje.setAlignment(Pos.BOTTOM_CENTER);

        btnContinuar = new Button("Continuar...");

// Agregar elementos al contenedor HBox
        btnContinuar.setAlignment(Pos.CENTER);
        btnContinuar.toFront();
        btnContinuar.setVisible(false);
        contenedorDialogo.getChildren().addAll(contImgPersonaje, dialogo, new Region(), btnContinuar);
    }

    private void cargarStatsJugador() {
        contenedorStatsJugador.setSpacing(10);
        contenedorStatsJugador.setAlignment(Pos.CENTER_RIGHT);
        nombreJugador.setAlignment(Pos.CENTER_LEFT);

        contenedorStatsJugador.getChildren().addAll(nombreJugador, iconoDineroJugador, dineroJugador);
    }

    private void actualizarNombreJugador() {
        nJugador = jugador.getNombre();
        nombreJugador = new Label(nJugador);
        nombreJugador.setMaxWidth(Double.MAX_VALUE);
        nombreJugador.setTextFill(Color.WHITE);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    }

    private void actualizarDineroJugador() {
        iconoDineroJugador = jugador.getIconoDineroJugador();

        dJugador = String.valueOf(jugador.getDinero());
        dineroJugador = new Label(dJugador + "$");
        dineroJugador.setTextFill(Color.WHITE);
    }

    public VBox getContenedorJuego() {
        return contenedorJuego;
    }

    public Label getDialogo() {
        return dialogo;
    }

    public Button getEleccion1() {
        return eleccion1;
    }

    public Button getEleccion2() {
        return eleccion2;
    }

    public Label getPregunta() {
        return preguntaEleccion;
    }

    public HBox getContenedorBotonEleccion() {
        return contenedorBotonEleccion;
    }

    public HBox getContenedorDialogo() {
        return contenedorDialogo;
    }

    public Label getPreguntaEleccion() {
        return preguntaEleccion;
    }

    public Map<String, String> getPathImgHistoria() {
        return pathImgHistoria;
    }

    public ImageView getImagenesHistoria() {
        return imagenesHistoria;
    }

    public Button getBtnContinuar() {
        return btnContinuar;
    }

    public ImageView getImagenPersonaje() {
        return imagenPersonaje;
    }

    private void cargarImagenesHistoria() {

        pathImgHistoria.put("Imagen 1", "resources/Acto1_Imagen1.png");
        pathImgHistoria.put("Imagen 2", "resources/Acto1_Imagen2.png");
        pathImgHistoria.put("Imagen 3", "resources/Acto1_Imagen3.png");

        imgHistoria = new Image(getPathImagen("Imagen 1"));
        imagenesHistoria = new ImageView(imgHistoria);

        imagenesHistoria.setFitHeight(350);
        imagenesHistoria.setFitWidth(600);

    }

    public String getPathImagen(String clave) {
        return pathImgHistoria.get(clave);

    }

}
