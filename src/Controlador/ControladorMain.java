/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.Transition;
import modelo.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Alerta;

/**
 *
 * @author Luis
 */
public class ControladorMain {

    //  Jugador
    private String nombreJugador;
    //  Inicio del juego
    private ControladorInicio cInicio;

    //  Eleccion de nombre del jugador
    private ControladorElegirNombre cElegirNombre;

    //  Acto 1 y eleccion de destino del jugador
    private ControladorActo1 cActo1;

    //  Eleccion Items
    private ControladorEleccionItems cItems;

    //  Minijuego
    private ControladorMinijuego cMinijuego;

    //  Menu Sierra Nevada
    private ControladorMenuSierraNevada cMenuSn;

    //  Menu Cahorros
    private ControladorMenuCahorros cMenuCahorros;

    private Alert alert;

    private Stage primaryStage;
    private StackPane pBase;

    public ControladorMain(Stage primaryStage) {
        this.cInicio = new ControladorInicio();
        this.cElegirNombre = new ControladorElegirNombre();
        this.cActo1 = new ControladorActo1();
        this.cItems = new ControladorEleccionItems();
        this.cMinijuego = new ControladorMinijuego();
        this.cMenuSn = new ControladorMenuSierraNevada();
        this.cMenuCahorros = new ControladorMenuCahorros();

        this.primaryStage = primaryStage;
        this.pBase = new StackPane();

    }

    public void iniciar() {
        configurarPantalla();

        cargarInicio();

        eventoCargarElegirNombre();

    }

    private void configurarPantalla() {

        pBase.getChildren().add(cInicio.getVistaInicio().getContenedorJuego());

        pBase.setId("pBase");

        Scene scene = new Scene(pBase, 850, 600);
        scene.getStylesheets().add(getClass().getResource("/estilos/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Aventura de Fin de Semana");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void cargarInicio() {
        pBase.getChildren().clear();
        pBase.getChildren().add(cInicio.getVistaInicio().getContenedorJuego());
    }

    private void mostrarElegirNombre() {
        pBase.getChildren().remove(cInicio.getVistaInicio().getContenedorJuego());
        pBase.getChildren().add(cElegirNombre.getContenedorJuego());

        cElegirNombre.iniciarEventos();
        eventosElegirNombre();
    }

    private void mostrarActo1() {

        actualizarStatsJugadorActo1();

        pBase.getChildren().remove(cElegirNombre.getContenedorJuego());

        pBase.getChildren().add(cActo1.getContenedorJuego());

        cActo1.iniciarEventos();

        eventoCambiarAEleccionItems();

    }

    private void mostrarEleccionItems() {

        pBase.getChildren().remove(cActo1.getContenedorJuego());
        pBase.getChildren().add(cItems.getContenedorJuego());
        eventoCargarMinijuego();

    }

    private void mostrarMinijuego() {

        actualizarStatsJugadorMinijuego();
        comprobarCaminoJugador();

        cItems.mostrarReglasMinijuego();

        Optional<ButtonType> result = reglasMinijuego("LEE LAS REGLAS, DESPUÉS ME CIERRAS.");

        if (result.isPresent()) {
            pBase.getChildren().remove(cItems.getContenedorJuego());
            pBase.getChildren().add(cMinijuego.getContenedorJuego());
            cMinijuego.iniciarEventos();
        }
        eventosMinijuego();
    }

    private void comprobarCaminoJugador() {

        Jugador jugador = Jugador.getInstanciaJugador();

        if (jugador.isCahorros()) {
            cMinijuego.getVistaM().cargarImagenesAlerta(1);

        } else if (jugador.isSierraNevada()) {
            cMinijuego.getVistaM().cargarImagenesAlerta(1);

        }
    }

    private void mostrarMenuSierraNevada() {
        pBase.getChildren().remove(cMinijuego.getContenedorJuego());

        actualizarStatsJugadorMenuSN();

        pBase.getChildren().add(cMenuSn.getContenedorJuego());

    }

    private void mostrarMenuCahorros() {
        pBase.getChildren().clear();

        actualizarStatsJugadorMenuCh();

        pBase.getChildren().add(cMenuCahorros.getContenedorJuego());

    }

    /*  EVENTOS    */
    private void eventoCargarElegirNombre() {

        //  Cambiar inicio a elegir nombre
        cInicio.getVistaInicio().getBtnInicio().setOnAction(event -> mostrarElegirNombre());

    }

    private void eventosElegirNombre() {

        cElegirNombre.getVistaElegirNombre().getBtnVolverInicio().setOnMouseClicked(event -> cargarInicio());

        cElegirNombre.getVistaElegirNombre().getBtnContinuarJuego().setOnMouseClicked(event -> {
            if (cElegirNombre.comprobarNombreJugador()) {

                mostrarActo1();

            }
        });

    }

    private void eventoCambiarAEleccionItems() {

        cActo1.getVistaActo1().getBtnContinuar().setOnAction(event -> mostrarEleccionItems());

    }

    private void eventoCargarMinijuego() {
        cItems.getBtnContinuarJuego().setOnAction(event -> {
            comprobarItems();
        });

    }

    private void comprobarItems() {

        Jugador jugador = Jugador.getInstanciaJugador();

        if (!jugador.getItems().isEmpty()) {
            mostrarMinijuego();

        } else {
            
        }
    }

    private void eventosMinijuego() {

        Jugador jugador = Jugador.getInstanciaJugador();

        cMinijuego.getVistaM().getBtnJuegoTerminado().setOnAction(event -> {
            if (jugador.isCahorros()) {
                mostrarMenuCahorros();
                cMinijuego.getVistaM().cargarImagenesAlerta(1);

            } else if (jugador.isSierraNevada()) {
                mostrarMenuSierraNevada();
                cMinijuego.getVistaM().cargarImagenesAlerta(0);

            }
        });

    }

    private void actualizarStatsJugadorActo1() {
        Jugador jugador = Jugador.getInstanciaJugador();

        cActo1.getVistaActo1().actualizarDineroJugador(jugador);
        cActo1.getVistaActo1().actualizarNombreJugador(jugador);

    }

    private void actualizarStatsJugadorMenuSN() {
        Jugador jugador = Jugador.getInstanciaJugador();

        cMenuSn.getvMenu().actualizarDineroJugador(jugador);
        cMenuSn.getvMenu().actualizarNombreJugador(jugador);

    }

    private void actualizarStatsJugadorMenuCh() {
        Jugador jugador = Jugador.getInstanciaJugador();

        cMenuCahorros.getVMenu().actualizarDineroJugador(jugador);
        cMenuCahorros.getVMenu().actualizarNombreJugador(jugador);

    }

    private void actualizarStatsJugadorMinijuego() {
        Jugador jugador = Jugador.getInstanciaJugador();

        cMinijuego.getVistaM().actualizarDineroJugador(jugador);
        cMinijuego.getVistaM().actualizarNombreJugador(jugador);
    }

    private Optional<ButtonType> reglasMinijuego(String message) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);

        return alert.showAndWait();
    }

    /*
    //  Acto 2
    private void mostrarActo2() {
        pBase.getChildren().clear();
        pBase.getChildren().add(panelActo1.getContenedorJuego());
    }

    private void mostrarActo3() {
        pBase.getChildren().clear();
        pBase.getChildren().add(panelActo1.getContenedorJuego());
    }

    private void mostrarActo4() {
        pBase.getChildren().clear();
        pBase.getChildren().add(panelActo1.getContenedorJuego());
    }
     */
}
