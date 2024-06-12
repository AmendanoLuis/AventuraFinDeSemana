/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Luis
 */
public class VistaElegirNombre {

    private final VBox panelElegirNombre;
    private TextField nombreField;
    private final HBox panelAcciones;
    private Button btnAceptarNombre;
    private Button btnContinuarJuego;
    private Button btnVolverInicio;
    private ImageView imagenElegirNombre;

    public VistaElegirNombre() {
        panelElegirNombre = new VBox(20);
        panelAcciones = new HBox(20);
        inicializarPanel();
    }

    private void inicializarPanel() {

        configurarImagen();

        configurarLabelNombre();

        configurarBotones();
    }

    private void configurarBotones() {
        // Configurar botones
        btnAceptarNombre = new Button("Establecer Nombre");
        btnContinuarJuego = new Button("Continuar Juego");
        btnVolverInicio = new Button("Volver al inicio");
        panelAcciones.getChildren().addAll(btnContinuarJuego, btnAceptarNombre, btnVolverInicio);
        panelAcciones.setAlignment(Pos.CENTER);
        panelElegirNombre.setAlignment(Pos.CENTER);
        panelElegirNombre.getChildren().add(panelAcciones);
    }

    private void configurarLabelNombre() {
        // Configurar el panel de introducción de nombre
        VBox contenedorTexto = new VBox(10);
        contenedorTexto.setPadding(new Insets(-20, 10, 10, 10));
        contenedorTexto.setAlignment(Pos.CENTER);

        Label textoNombre = new Label("Introduce tu nombre");
        textoNombre.setTextFill(Color.WHITE);
        nombreField = new TextField();
        nombreField.setMinSize(100, 20);

        contenedorTexto.getChildren().addAll(textoNombre, nombreField);

        panelElegirNombre.getChildren().add(contenedorTexto);
    }

    private void configurarImagen() {
        // Configurar la imagen
        panelElegirNombre.setSpacing(70);
        panelElegirNombre.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        imagenElegirNombre = new ImageView(new Image("resources/elegirNombre.png"));
        imagenElegirNombre.setFitWidth(650);
        imagenElegirNombre.setFitHeight(350);
        imagenElegirNombre.setPreserveRatio(true);
        imagenElegirNombre.setSmooth(true);
        imagenElegirNombre.setCache(true);
        imagenElegirNombre.setPickOnBounds(true);

        VBox.setVgrow(imagenElegirNombre, Priority.ALWAYS);
        panelElegirNombre.getChildren().add(imagenElegirNombre);

    }

  

    public VBox getContenedorJuego() {
        return panelElegirNombre;
    }

    public ImageView getImagenElegirNombre() {
        return imagenElegirNombre;
    }

    public Button getBtnVolverInicio() {
        return btnVolverInicio;
    }

    public TextField getNombreJugador() {
        return nombreField;
    }

    public Button getBtnAceptarNombre() {
        return btnAceptarNombre;
    }

    public Button getBtnContinuarJuego() {
        return btnContinuarJuego;
    }

}
