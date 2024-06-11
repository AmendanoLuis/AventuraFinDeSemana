/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.Optional;
import modelo.Jugador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class ControladorMain {

    //  Jugador
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

    private String instruccionesHistoria = "Haz click en la imagen para continuar la historia, ve con cuidado, podrias tener elecciones importantes.";

    private String instruccionesEleccionItems = "Elige los items y la cantidad de dinero que creas necesarios."
            + "\nLee atentamente para que sirven los objetos, te pueden ser muy utiles."
            + "\nY no te olvides del dinero";

    private String reglasMinijuego = "REGLAS NO SE";

    private Alert alerta;

    private Stage primaryStage;
    private StackPane pBase;

    public ControladorMain(Stage primaryStage) {
        this.cInicio = new ControladorInicio();
        this.cElegirNombre = new ControladorElegirNombre();
        this.cActo1 = new ControladorActo1();
        this.cItems = new ControladorEleccionItems();
        this.cMinijuego = new ControladorMinijuego();

        this.primaryStage = primaryStage;
        this.pBase = new StackPane();
    }

    public void iniciar() {
        //Configuramos la pantalla: tamaño, padding, color, etc
        configurarPantalla();

        //Configuramos los eventos que pueden suceder en el juego
        cambiarVistaElegirNombre();

        //Comenzamos el juego
        mostrarInicio();
    }

    private void configurarPantalla() {

        pBase.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pBase.getChildren().add(cInicio.getVistaInicio().getContenedorJuego());

        Scene scene = new Scene(pBase, 850, 600);
        scene.getStylesheets().add(getClass().getResource("/estilos/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Aventura de Fin de Semana");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void cambiarVistaElegirNombre() {

        //  Cambiar inicio a elegir nombre
        cInicio.getVistaInicio().getBtnInicio().setOnAction(event -> mostrarElegirNombre());

    }

    private void configuracionEventosElegirNombre() {

        //  Boton Volver al Inicio en pantalla elegir nombre
        cElegirNombre.getVistaElegirNombre().getBtnVolverInicio().setOnAction(event -> mostrarInicio());

        //  Boton aceptar obtener nombre jugador
        cElegirNombre.getVistaElegirNombre().getBtnAceptarNombre().setOnAction(event -> aceptarNombre());

        //  Boton continuar juego
        cElegirNombre.getVistaElegirNombre().getBtnContinuarJuego().setOnAction(event -> mostrarActo1());

    }

    private void cambiarVistaEleccionItems() {

        // Cargar evento de continuar juego
        cActo1.getVistaActo1().getBtnContinuar().setOnAction(event -> mostrarEleccionItems());

    }

    //Acto 1
    private void mostrarActo1() {
        Jugador jugador = Jugador.getInstanciaJugador();

        alerta(instruccionesHistoria);

        if (jugador.getNombre() != null) {
            this.cActo1 = new ControladorActo1();
            pBase.getChildren().clear();
            pBase.getChildren().add(cActo1.getContenedorJuego());
            cambiarVistaEleccionItems();

            // Habilitar el botón de continuar
            cActo1.getVistaActo1().getBtnContinuar().setDisable(false);
        } else {
            alerta("¡Cuidado!¡No nos has dicho tu nombre!");

        }

    }

    private void cargarMinijuego() {

        cItems.getBtnContinuarJuego().setOnAction(event -> mostrarMinijuego());
    }

    //  Inicio
    private void mostrarInicio() {
        pBase.getChildren().clear();
        pBase.getChildren().add(cInicio.getVistaInicio().getContenedorJuego());
    }

    private void mostrarElegirNombre() {
        pBase.getChildren().clear();
        pBase.getChildren().add(cElegirNombre.getVistaElegirNombre().getContenedorJuego());
        configuracionEventosElegirNombre();
    }

    private void mostrarEleccionItems() {

        alerta(instruccionesEleccionItems);

        pBase.getChildren().clear();
        pBase.getChildren().add(cItems.getContenedorJuego());
        cargarMinijuego();

    }

    private void mostrarMinijuego() {
        
        actualizarStatsJugadorMinijuego();
        // Crear y mostrar la alerta, esperando hasta que el usuario la cierre
        Optional<ButtonType> result = reglasMinijuego(reglasMinijuego);

        // Continuar solo después de que se haya cerrado la alerta
        if (result.isPresent()) {
            pBase.getChildren().clear();
            StackPane centeredPane = new StackPane(cMinijuego.getContenedorJuego());
            centeredPane.setAlignment(Pos.CENTER);
            pBase.getChildren().add(centeredPane);
            cMinijuego.iniciarEventos();
        }
    }

    private void aceptarNombre() {
        Jugador jugador = Jugador.getInstanciaJugador();

        String nombreJugador = cElegirNombre.getVistaElegirNombre().getNombreJugador().getText();

        jugador.setNombre(nombreJugador);
        alerta("¡Nombre ingresado correctamente!");
    }

    private void alerta(String message) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.show();

    }

    private void actualizarStatsJugadorMinijuego() {
        Jugador jugador = Jugador.getInstanciaJugador();

        cMinijuego.getVistaM().actualizarDineroJugador(jugador);
        cMinijuego.getVistaM().actualizarNombreJugador(jugador);
    }

    private Optional<ButtonType> reglasMinijuego(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Mostrar la alerta y esperar hasta que el usuario la cierre
        return alert.showAndWait();
    }

    /*
    //  Minijuego
    private void mostrarMinijuego() {
        pBase.getChildren().clear();
        pBase.getChildren().add(panelActo1.getContenedorJuego());
    }

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
