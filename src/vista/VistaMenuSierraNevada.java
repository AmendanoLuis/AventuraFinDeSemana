/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modelo.Jugador;
import lombok.Getter;
import lombok.Setter;
import personajes.MariaTrueno;

/**
 *
 * @author user
 */
@Getter
@Setter
public class VistaMenuSierraNevada {

    private Image imagenFondo;
    private ImageView fondo;

    private Label textRest;
    private Image imagenRestaurante;
    private ImageView restaurante;
    private StackPane cRestaurante;

    private Label textViaje;
    private Image imagenViajeTrineo;
    private ImageView viajeTrineo;
    private StackPane cViajeTrineo;

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

    public VistaMenuSierraNevada() {

        contenedorJuego = new Pane();

        iniciarImagenes();
        establecerDiseño();
    }

    private void iniciarImagenes() {

        imagenFondo = new Image("resources/menuSierraNevadaFondo.png");
        fondo = new ImageView(imagenFondo);

        textViaje = new Label("Viaje en Trineo");
        imagenViajeTrineo = new Image("resources/viajeTrineo.png");
        viajeTrineo = new ImageView(imagenViajeTrineo);
        cViajeTrineo = new StackPane();

        textRest = new Label("Restaurante");
        imagenRestaurante = new Image("resources/restauranteSierraNevada.png");
        restaurante = new ImageView(imagenRestaurante);
        cRestaurante = new StackPane();

        textPaseo = new Label("Paseo");
        imagenPaseo = new Image("resources/paseoSierraNevada.png");
        paseo = new ImageView(imagenPaseo);
        cPaseo = new StackPane();

    }

    private void establecerDiseño() {

        fondo.setFitHeight(600);
        fondo.setFitWidth(850);
        contenedorJuego.getChildren().add(fondo);

        cargarViajeTrineo();
        cargarRestaurante();
        cargarPaseo();
        iniciarStatsJugador();
        cargarContenedorDialogo();
        cargarMenuContextual();
        añadirId();
    }

    private void cargarViajeTrineo() {

        contenedorJuego.getChildren().add(textViaje);

        textViaje.setLayoutX(130);
        textViaje.setLayoutY(150);

        textViaje.setVisible(false);

        viajeTrineo.setFitHeight(340);
        viajeTrineo.setFitWidth(307);

        cViajeTrineo.getChildren().add(viajeTrineo);
        contenedorJuego.getChildren().add(cViajeTrineo);

        cViajeTrineo.setLayoutX(0);
        cViajeTrineo.setLayoutY(160);
    }

    private void cargarRestaurante() {

        contenedorJuego.getChildren().add(textRest);

        textRest.setLayoutX(660);
        textRest.setLayoutY(160);

        textRest.setVisible(false);

        restaurante.setFitHeight(309);
        restaurante.setFitWidth(345);

        cRestaurante.getChildren().add(restaurante);
        contenedorJuego.getChildren().add(cRestaurante);

        cRestaurante.setLayoutX(510);
        cRestaurante.setLayoutY(180);

    }

    private void cargarPaseo() {

        contenedorJuego.getChildren().add(textPaseo);

        textPaseo.setLayoutX(340);
        textPaseo.setLayoutY(280);

        textPaseo.setVisible(false);

        paseo.setFitHeight(150);
        paseo.setFitWidth(90);

        cPaseo.getChildren().add(paseo);
        contenedorJuego.getChildren().add(cPaseo);

        cPaseo.setLayoutX(340);
        cPaseo.setLayoutY(300);
    }

    private void iniciarStatsJugador() {
        Jugador jugador = Jugador.getInstanciaJugador();

        nombreJugador = new Label(" ");
        nombreJugador.setMaxWidth(Double.MAX_VALUE);
        nombreJugador.setTextFill(Color.WHITE);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        dineroJugador = new Label(" ");
        dineroJugador.setTextFill(Color.WHITE);
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

        dialogo = new Label("Bienvenido a Sierra Nevada");
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
        textViaje.setId("textViaje");
        panelDialogo.setId("panelDialogoSierraNevada");
        dialogo.setId("dialogoSierraNevada");
        menuAcciones.setId("menuAcciones");
        contenedorStatsJugador.setId("statsJugador");
        nombreJugador.setId("nombreJugador");
        dineroJugador.setId("dineroJugador");
    }

    public StackPane getcRestaurante() {
        return cRestaurante;
    }

    public StackPane getcViajeTrineo() {
        return cViajeTrineo;
    }

    public StackPane getcPaseo() {
        return cPaseo;
    }

}
