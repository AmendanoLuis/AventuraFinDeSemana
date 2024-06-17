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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.*;
import modelo.Alerta;

/**
 *
 * @author Luis
 */
@Data
public class VistaElegirNombre {

    private Button btnAceptarNombre;
    private Button btnContinuarJuego;
    private Button btnVolverInicio;

    private Label textoNombre;
    private TextField nombreField;

    private ImageView imagenElegirNombre;

    private VBox contenedorTexto;
    private VBox vElegirNombre;
    private HBox cBotones;

    private StackPane contenedorJuego;

    public VistaElegirNombre() {
        vElegirNombre = new VBox(20);
        cBotones = new HBox(20);
        inicializarPanel();

    }

    private void inicializarPanel() {

        iniciarComponentes();

        configurarImagen();

        configurarLabelNombre();

        configurarBotones();

        configurarPanel();


    }

    private void configurarPanel() {
        vElegirNombre.setAlignment(Pos.CENTER);

        contenedorJuego.getChildren().add(vElegirNombre);
    }

    private void iniciarComponentes() {
        btnAceptarNombre = new Button("Elegir nombre");
        btnContinuarJuego = new Button("Continuar juego");
        btnVolverInicio = new Button("Volver al inicio");

        contenedorTexto = new VBox(10);
        contenedorTexto.setPadding(new Insets(10));

        textoNombre = new Label("Introduce tu nombre");
        nombreField = new TextField();

        contenedorJuego = new StackPane();

    }

    private void configurarBotones() {

        cBotones.getChildren().addAll(btnContinuarJuego, btnAceptarNombre, btnVolverInicio);
        cBotones.setAlignment(Pos.CENTER);

        vElegirNombre.getChildren().add(cBotones);
    }

    private void configurarLabelNombre() {

        textoNombre.setTextFill(Color.WHITE);
        nombreField.setMinSize(100, 20);

        contenedorTexto.getChildren().addAll(textoNombre, nombreField);
        contenedorTexto.setAlignment(Pos.CENTER);

        vElegirNombre.getChildren().add(contenedorTexto);
    }

    private void configurarImagen() {

        vElegirNombre.setSpacing(70);
        imagenElegirNombre = new ImageView(new Image("resources/elegirNombre.png"));
        imagenElegirNombre.setFitWidth(350);
        imagenElegirNombre.setFitHeight(250);

        vElegirNombre.getChildren().add(imagenElegirNombre);

    }

}
