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
    private StackPane stackImagenPersonaje;
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
        cargarImagenesHistoria();

    }

    private void inicializarComponentes() {

        jugador = Jugador.getInstanciaJugador();
        dJugador = String.valueOf(jugador.getDinero());
        pathImgHistoria = new HashMap<>();
        stackImagenPersonaje = new StackPane();

        // Inicializa y configura los componentes de la interfaz gráfica
        contenedorJuego = new VBox(20);
        actualizarNombreJugador();

        // Nombre Jugador
        nJugador = jugador.getNombre();
        nombreJugador = new Label(nJugador);
        nombreJugador.setMaxWidth(Double.MAX_VALUE); // El ancho del Label se expandirá al máximo
        nombreJugador.setTextFill(Color.WHITE); // Establece el color del texto
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        //  Dinero Jugador
        iconoDineroJugador = jugador.getIconoDineroJugador();
        actualizarDineroJugador();

        //  Contenedor Stats Jugador
        contenedorStatsJugador = new HBox(10);
        contenedorStatsJugador.setSpacing(10);
        contenedorStatsJugador.setAlignment(Pos.CENTER_RIGHT);
        nombreJugador.setAlignment(Pos.CENTER_LEFT);

        contenedorStatsJugador.getChildren().addAll(nombreJugador, iconoDineroJugador, dineroJugador);

        //  Cargamos nuestra primera imagen como base, despues la cambiaremos
        //  en el controlador si lo necesitamos
        cargarImagenesHistoria();
        imgHistoria = new Image(getPathImagen("Imagen 1"));
        imagenesHistoria = new ImageView(imgHistoria);

        //  Altura imagen
        imagenesHistoria.setFitHeight(350);
        // Ancho imagen
        imagenesHistoria.setFitWidth(600);

        //  Dialogo
        dialogo = new Label();
        // Establece el tamaño de fuente y el color del texto
        dialogo.setFont(Font.font(22)); // Tamaño de fuente 22
        dialogo.setTextFill(Color.WHITE); // Color blanco

        contenedorDialogo = new HBox();
        contenedorDialogo.setAlignment(Pos.CENTER_LEFT);

        //  Cargamos contenedor historia y dialogo
        imgPersonaje = new Image("images/hellerEmote/poseHellerNormal.png");
        imagenPersonaje = new ImageView(imgPersonaje);

        // Ajusta la altura y el ancho de la imagen
        imagenPersonaje.setFitWidth(70); // ajusta el ancho de la imagen a 50 píxeles
        imagenPersonaje.setFitHeight(130); // ajusta la altura de la imagen a 50 píxeles
        // Establece la posición Z del ImageView para asegurarse de que esté al frente
        imagenPersonaje.toFront();

        // Agrega la imagen del personaje y el diálogo al StackPane
        stackImagenPersonaje.getChildren().add(imagenPersonaje);
        stackImagenPersonaje.setAlignment(Pos.BOTTOM_CENTER);

        btnContinuar = new Button("Continuar... ");
        btnContinuar.setStyle("-fx-background-color: #008287; " +
                      "-fx-border-color: #FFFFFF; " +
                      "-fx-border-width: 2; " +
                      "-fx-text-fill: #FFFFFF; " +
                      "-fx-font-family: 'Times New Roman'; " +
                      "-fx-font-style: italic; " +
                      "-fx-font-size: 16px; " +
                      "-fx-background-radius: 10 10 10 10; " +
                      "-fx-border-radius: 10 10 10 10;");
        

        contenedorDialogo.getChildren().addAll(stackImagenPersonaje, dialogo, new Region(), btnContinuar);
        HBox.setHgrow(new Region(), Priority.ALWAYS);
        btnContinuar.setAlignment(Pos.CENTER_RIGHT);
        btnContinuar.setVisible(false);
        
        //  Cargamos contenedor eleccion
        eleccion1 = new Button("Sierra Nevada");
        eleccion2 = new Button("Cahorros");
        preguntaEleccion = new Label("¿Dónde quieres viajar?");
        contenedorBotonEleccion = new HBox();
        contenedorBotonEleccion.setAlignment(Pos.CENTER);
        contenedorBotonEleccion.getChildren().addAll(eleccion1, eleccion2);

        //  Cargamos configuracion base de acto1
        contenedorJuego.setPadding(new Insets(-20, 10, 10, 10));
        contenedorJuego.setAlignment(Pos.CENTER);
        contenedorJuego.getChildren().addAll(contenedorStatsJugador, imagenesHistoria, contenedorDialogo);
    }

    private void actualizarNombreJugador() {
        nJugador = jugador.getNombre();
        nombreJugador = new Label("Jugador: " + nJugador);
        nombreJugador.setMaxWidth(Double.MAX_VALUE);
        nombreJugador.setTextFill(Color.WHITE);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    }

    private void actualizarDineroJugador() {
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

    private void cargarImagenesHistoria() {

        pathImgHistoria.put("Imagen 1", "images/panelActo1/Acto1_Imagen1.png");
        pathImgHistoria.put("Imagen 2", "images/panelActo1/Acto1_Imagen2.png");
        pathImgHistoria.put("Imagen 3", "images/panelActo1/Acto1_Imagen3.png");

    }

    public String getPathImagen(String clave) {
        return pathImgHistoria.get(clave);

    }

}
