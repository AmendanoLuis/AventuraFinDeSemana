/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modelo.Jugador;
import lombok.*;

/**
 *
 * @author user
 */
@Data
public class VistaMenuCahorros {

    private Image imagenFondo;
    private ImageView fondo;

    private Label textRest;
    private Image imagenRestaurante;
    private ImageView restaurante;
    private StackPane cRestaurante;

    private Label textPaseo;
    private Image imagenPaseo;
    private ImageView paseo;
    private StackPane cPaseo;

    private String nJugador;
    private Label nombreJugador;
    private ImageView iconoDineroJugador;
    private String dJugador;
    private Label dineroJugador;
    private HBox contenedorStatsJugador;

    private StackPane panelDialogo;
    private HBox panelControlJuego;
    private String pathImage;
    private Image imgPersonaje;
    private ImageView personaje;
    private Label dialogo;

    private ContextMenu menuAcciones;
    private MenuItem entrar;
    private MenuItem info;
    private MenuItem salir;

    private Pane contenedorJuego;

    private StackPane pausaDialogo;

    public VistaMenuCahorros() {

        contenedorJuego = new Pane();

        iniciarImagenes();
        establecerDiseño();
    }

    private void iniciarImagenes() {

        imagenFondo = new Image("resources/menuCahorrosFondo.png");
        fondo = new ImageView(imagenFondo);

        textRest = new Label("Restaurante");
        imagenRestaurante = new Image("resources/restauranteCahorros.png");
        restaurante = new ImageView(imagenRestaurante);
        cRestaurante = new StackPane();

        textPaseo = new Label("Paseo");
        imagenPaseo = new Image("resources/paseoCahorros.png");
        paseo = new ImageView(imagenPaseo);
        cPaseo = new StackPane();

    }

    private void establecerDiseño() {

        fondo.setFitHeight(600);
        fondo.setFitWidth(850);
        contenedorJuego.getChildren().add(fondo);

        cargarRestaurante();
        cargarPaseo();
        iniciarStatsJugador();
        cargarContenedorDialogo();
        cargarMenuContextual();
        añadirId();
    }

    private void cargarRestaurante() {

        contenedorJuego.getChildren().add(textRest);

        textRest.setLayoutX(660);
        textRest.setLayoutY(100);

        textRest.setVisible(false);

        restaurante.setFitHeight(290);
        restaurante.setFitWidth(460);

        cRestaurante.getChildren().add(restaurante);
        contenedorJuego.getChildren().add(cRestaurante);

        cRestaurante.setLayoutX(380);
        cRestaurante.setLayoutY(90);
    }

    private void cargarPaseo() {

        contenedorJuego.getChildren().add(textPaseo);

        textPaseo.setLayoutX(130);
        textPaseo.setLayoutY(120);

        textPaseo.setVisible(false);

        paseo.setFitHeight(150);
        paseo.setFitWidth(100);

        cPaseo.getChildren().add(paseo);
        contenedorJuego.getChildren().add(cPaseo);

        cPaseo.setLayoutX(110);
        cPaseo.setLayoutY(130);
    }

    private void iniciarStatsJugador() {
        Jugador jugador = Jugador.getInstanciaJugador();

        nombreJugador = new Label(" ");
        nombreJugador.setMaxWidth(Double.MAX_VALUE);

        dineroJugador = new Label(" ");
        iconoDineroJugador = jugador.getIconoDineroJugador();
        iconoDineroJugador.setFitHeight(30);
        iconoDineroJugador.setFitWidth(30);

        contenedorStatsJugador = new HBox(10);
        contenedorStatsJugador.setPadding(new Insets(10));
        contenedorStatsJugador.setSpacing(10);
        contenedorStatsJugador.setAlignment(Pos.CENTER_RIGHT);
        nombreJugador.setAlignment(Pos.CENTER_LEFT);

        contenedorStatsJugador.getChildren().addAll(nombreJugador, iconoDineroJugador, dineroJugador);

        contenedorJuego.getChildren().add(contenedorStatsJugador);
        contenedorStatsJugador.setLayoutX(0);
        contenedorStatsJugador.setLayoutY(0);

    }

    public void actualizarNombreJugador(Jugador jugador) {

        nJugador = jugador.getNombre();
        nombreJugador.setText(nJugador + " | ");
    }

    public void actualizarDineroJugador(Jugador jugador) {

        dJugador = String.valueOf(jugador.getDinero());
        dineroJugador.setText(dJugador + " $");
    }

    private void cargarContenedorDialogo() {

        pausaDialogo = new StackPane();
        pausaDialogo.setMinSize(850, 600);
        contenedorJuego.getChildren().add(pausaDialogo);
        pausaDialogo.setVisible(false);

        pathImage = "resources/personajeImagen.png";
        imgPersonaje = new Image(pathImage);
        personaje = new ImageView(imgPersonaje);
        personaje.setFitHeight(100);
        personaje.setFitWidth(70);

        dialogo = new Label("Bienvenido a los Cahorros");
        dialogo.setPadding(new Insets(20));
        dialogo.setWrapText(true);
        dialogo.setMinWidth(600);
        dialogo.setMaxWidth(600);

        panelDialogo = new StackPane();
        panelControlJuego = new HBox(20);
        panelControlJuego.setPadding(new Insets(5));
        panelControlJuego.setMinSize(795, 110);

        panelControlJuego.getChildren().addAll(personaje, dialogo);
        panelDialogo.getChildren().add(panelControlJuego);
        panelDialogo.setLayoutY(460);

        contenedorJuego.getChildren().add(panelDialogo);
        panelDialogo.toFront();

    }

    private void cargarMenuContextual() {
        menuAcciones = new ContextMenu();
        entrar = new MenuItem("ENTRAR");
        info = new MenuItem("INTERACTUAR");
        salir = new MenuItem("SALIR");
        menuAcciones.getItems().addAll(entrar, info, salir);

    }

    private void añadirId() {

        textPaseo.setId("textPaseo");
        textRest.setId("textRest");
        panelDialogo.setId("panelDialogoSierraNevada");
        dialogo.setId("dialogoSierraNevada");
        menuAcciones.setId("menuAcciones");
        contenedorStatsJugador.setId("statsJugador");
        nombreJugador.setId("nombreJugador");
        dineroJugador.setId("dineroJugador");
    }

}
