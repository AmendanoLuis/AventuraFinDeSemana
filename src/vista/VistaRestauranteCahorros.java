/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.Jugador;
import lombok.Data;

/**
 *
 * @author Luis
 */
@Data
public class VistaRestauranteCahorros {

    private String nJugador = "";

    private Label nombreJugador;
    private ImageView iconoDineroJugador;
    private String dJugador;

    private Label dineroJugador;
    private HBox contenedorStatsJugador;

    private Map<String, String> pathImgHistoria;
    private Image imgHistoria;
    private ImageView imagenesHistoria;
    private StackPane cPasarDialogos;

    private Label dialogo;
    private StackPane contImgPersonaje;
    private Image imgPersonaje;
    private ImageView imagenPersonaje;

    private StackPane cContinuar;
    private Button btnContinuar;

    private Button eleccion1;
    private Button eleccion2;
    private Label preguntaEleccion;

    private VBox cPregunta;
    private StackPane contenedorDialogoyEleccion;
    private HBox contenedorBotonEleccion;
    private HBox contenedorDialogo;

    private StackPane contenedorJuego;
    private VBox vContenedorJuego;

    public VistaRestauranteCahorros() {

        inicializarComponentes();
        iniciarBloqueInvisibleDialogo();
        setId();
    }

    private void inicializarComponentes() {
        Jugador jugador = Jugador.getInstanciaJugador();

        contenedorJuego = new StackPane();
        vContenedorJuego = new VBox(20);

        dineroJugador = new Label("");
        iconoDineroJugador = new ImageView(jugador.getImgDineroJugador());
        nombreJugador = new Label("");
        contenedorStatsJugador = new HBox(10);

        pathImgHistoria = new HashMap<>();
        dialogo = new Label();

        contImgPersonaje = new StackPane();

        contenedorDialogoyEleccion = new StackPane();

        contenedorDialogo = new HBox(20);

        cPregunta = new VBox(20);

        cPasarDialogos = new StackPane();

        contenedorBotonEleccion = new HBox(30);

        cContinuar = new StackPane();
        btnContinuar = new Button("Continuar...");

        configurarComponentes();
    }

    private void configurarComponentes() {
        cargarImagenesHistoria();
        cargarContenedorDialogoyEleccion();
        cargarContenedorEleccion();
        cargarContenedorDialogo();
        cargarStatsJugador();
        cargarBotonContinuar();

        vContenedorJuego.setPadding(new Insets(-20, 10, 10, 10));
        vContenedorJuego.setAlignment(Pos.CENTER);
        vContenedorJuego.getChildren().addAll(contenedorStatsJugador, imagenesHistoria, contenedorDialogoyEleccion);

        contenedorJuego.getChildren().add(vContenedorJuego);
    }

    private void cargarContenedorDialogoyEleccion() {
        contenedorDialogoyEleccion.setMinSize(800, 110);

        contenedorDialogoyEleccion.getChildren().add(contenedorDialogo);

        contenedorDialogoyEleccion.setLayoutX(10);
        contenedorDialogoyEleccion.setLayoutY(460);

    }

    private void cargarContenedorEleccion() {

        preguntaEleccion = new Label("");

        eleccion1 = new Button("");
        eleccion2 = new Button("");

        contenedorBotonEleccion.getChildren().addAll(eleccion1, eleccion2);
        contenedorBotonEleccion.setAlignment(Pos.CENTER);

        cPregunta.getChildren().addAll(preguntaEleccion, contenedorBotonEleccion);
        cPregunta.setAlignment(Pos.CENTER);

    }

    private void cargarContenedorDialogo() {

        dialogo.setMaxWidth(600);
        dialogo.setMinHeight(110);
        dialogo.setWrapText(true);

        imgPersonaje = null;
        imagenPersonaje = new ImageView(imgPersonaje);

        imagenPersonaje.setFitWidth(70);
        imagenPersonaje.setFitHeight(130);

        contImgPersonaje.getChildren().add(imagenPersonaje);
        contImgPersonaje.setAlignment(Pos.BOTTOM_CENTER);

        contenedorDialogo.getChildren().addAll(contImgPersonaje, dialogo);
    }

    private void cargarBotonContinuar() {
        cContinuar.getChildren().add(btnContinuar);

        contenedorDialogo.getChildren().add(cContinuar);

        cContinuar.setVisible(false);
    }

    private void iniciarBloqueInvisibleDialogo() {
        cPasarDialogos = new StackPane();
        cPasarDialogos.setMinSize(850, 600);
        contenedorJuego.getChildren().add(cPasarDialogos);

        cPasarDialogos.setVisible(false);
    }

    private void cargarStatsJugador() {
        contenedorStatsJugador.setSpacing(10);
        contenedorStatsJugador.setAlignment(Pos.CENTER_RIGHT);

        iconoDineroJugador.setFitHeight(20);
        iconoDineroJugador.setFitWidth(20);

        contenedorStatsJugador.getChildren().addAll(nombreJugador, iconoDineroJugador, dineroJugador);
    }

    public void actualizarNombreJugador(Jugador jugador) {

        nJugador = jugador.getNombre();
        nombreJugador.setText(nJugador + " | ");
    }

    public void actualizarDineroJugador(Jugador jugador) {

        dJugador = String.valueOf(jugador.getDinero());
        dineroJugador.setText(dJugador + " $");
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

    private void setId() {
        this.preguntaEleccion.setId("preguntaDecision");
        this.dialogo.setId("dialogo");
        this.contenedorStatsJugador.setId("statsJugadorHistoria");
        this.nombreJugador.setId("nombreJugador");
        this.dineroJugador.setId("dineroJugador");
    }

    public String getPathImagen(String clave) {
        return pathImgHistoria.get(clave);

    }

}
