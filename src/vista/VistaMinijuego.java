/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modelo.Jugador;
import personajes.MariaTrueno;
import lombok.*;
import modelo.Alerta;

/**
 *
 * @author Luis
 */
@Data
public class VistaMinijuego {

    private Image imagenCarretera;
    private ImageView carreteraFondo;
    private Image imagenCoche;
    private ImageView coche;

    private Image imgJuegoPerdidoSn;
    private Image imgJuegoPerdidoCh;

    private Image imgPausaSn;
    private Image imgPausaCh;

    private Image imgJuegoGanadoSn;
    private Image imgJuegoGanadoCh;

    private String nJugador;

    private Label nombreJugador;
    private ImageView iconoDineroJugador;
    private String dJugador;

    private Label dineroJugador;
    private HBox contenedorStatsJugador;

    private StackPane cControlJuego;
    private HBox panelControlJuego;
    private MariaTrueno mariaTrueno;
    private String pathImage;
    private Image imgPersonaje;
    private ImageView personaje;
    private Label dialogo;
    private Button btnParar;
    private StackPane cBoton;

    private StackPane cBotonTerminarMinijuego;
    private Button btnJuegoTerminado;

    private Pane contenedorJuego;

    public VistaMinijuego() {
        contenedorJuego = new Pane();
        iniciarImagenes();
        iniciarStatsJugador();
        cargarControlJuego();
        cargarAlerta();
        diseñoAlerta();

        asignarId();
    }

    private void iniciarImagenes() {

        cargarImagenesAlerta(1);

        //  Iniciar imagenes
        imagenCarretera = new Image("resources/carreteraFondo.png");
        carreteraFondo = new ImageView(imagenCarretera);
        carreteraFondo.setFitWidth(850);
        carreteraFondo.setFitHeight(600);

        imagenCoche = new Image("resources/coche.png");
        coche = new ImageView(imagenCoche);

        //  Tamaño coche
        coche.setFitHeight(140);
        coche.setFitWidth(280);

        contenedorJuego.getChildren().addAll(carreteraFondo, coche);

        //  Posición Inicial Coche
        coche.setLayoutX(0);
        coche.setLayoutY(350);

    }

    public void cargarImagenesAlerta(int i) {

        if (i == 1) {

            imgJuegoPerdidoCh = new Image("resources/juegoPerdido.png");
            imgPausaCh = new Image("resources/fotoEsperaSierraNevada.png");
            imgJuegoGanadoCh = new Image("resources/juegoGanadoCahorros.png");

        } else if (i == 0) {
            imgJuegoPerdidoSn = new Image("resources/juegoPerdido.png");
            imgJuegoGanadoSn = new Image("resources/juegoGanadoSierraNevada.png");
            imgPausaSn = new Image("resources/fotoEsperaSierraNevada.png");

        }
    }

    private void iniciarStatsJugador() {
        Jugador jugador = Jugador.getInstanciaJugador();

        nombreJugador = new Label(" ");
        nombreJugador.setMaxWidth(Double.MAX_VALUE);
        nombreJugador.setTextFill(Color.WHITE);
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        //  Dinero Jugador
        dineroJugador = new Label(" ");
        dineroJugador.setTextFill(Color.WHITE);
        iconoDineroJugador = jugador.getIconoDineroJugador();

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

    private void cargarControlJuego() {

        mariaTrueno = new MariaTrueno();
        pathImage = mariaTrueno.obtenerPose("p2");

        imgPersonaje = new Image(pathImage);
        personaje = new ImageView(imgPersonaje);
        personaje.setFitHeight(100);
        personaje.setFitWidth(70);

        dialogo = new Label("QUE ME MEOOOOOOOOOO");
        dialogo.setPadding(new Insets(20));
        dialogo.setMinWidth(590);

        btnParar = new Button("STOP");

        cBoton = new StackPane();
        cBoton.setMinWidth(120);
        cBoton.setPadding(new Insets(10));

        cBoton.getChildren().add(btnParar);

        cControlJuego = new StackPane();
        panelControlJuego = new HBox(20);
        panelControlJuego.setPadding(new Insets(5));
        panelControlJuego.setMinSize(810, 110);
        panelControlJuego.setMaxSize(840, 110);

        panelControlJuego.getChildren().addAll(personaje, dialogo, cBoton);
        btnParar.setAlignment(Pos.CENTER_RIGHT);
        cControlJuego.getChildren().add(panelControlJuego);
        cControlJuego.setLayoutY(460);

        contenedorJuego.getChildren().add(cControlJuego);

    }

    private void cargarAlerta() {

        this.cBotonTerminarMinijuego = new StackPane();
        this.btnJuegoTerminado = new Button("Salir del coche");
    }

    private void diseñoAlerta() {
        cBotonTerminarMinijuego.getChildren().add(btnJuegoTerminado);
        contenedorJuego.getChildren().add(cBotonTerminarMinijuego);
        cBotonTerminarMinijuego.setLayoutX(100);
        cBotonTerminarMinijuego.setLayoutY(520);
        cBotonTerminarMinijuego.setVisible(false);
    }

    private void asignarId() {
        panelControlJuego.setId("panelControlJuego");
        cBoton.setId("contenedor");
        btnParar.setId("btnParar");
        dialogo.setId("dialogo");

        contenedorStatsJugador.setId("statsJugador");
        nombreJugador.setId("nombreJugador");
        dineroJugador.setId("dineroJugador");

    }

    public Image getImgPersonajeP1() {
        pathImage = mariaTrueno.obtenerPose("p2");

        return imgPersonaje;
    }

    public Image getImgPersonajeP2() {
        pathImage = mariaTrueno.obtenerPose("p1");
        return imgPersonaje;
    }

}
