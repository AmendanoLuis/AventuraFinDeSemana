/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import modelo.Jugador;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vista.VistaInicio;

/**
 *
 * @author Luis
 */
public class ControladorMain {

    //  Jugador
    private ControladorJugador cJugador;

    //  Inicio del juego
    private ControladorInicio cInicio;

    //  Eleccion de nombre del jugador
    private ControladorElegirNombre cElegirNombre;

    //  Acto 1 y eleccion de destino del jugador
    private ControladorActo1 cActo1;

    //  Eleccion Items
    private ControladorEleccionItems cItems;

    private String instruccionesHistoria = "Haz click en la imagen para continuar la historia, ve con cuidado, podrias tener elecciones importantes.";

    private String instruccionesEleccionItems = "Elige los items y la cantidad de dinero que creas necesarios."
            + "\nLee atentamente para que sirven los objetos, te pueden ser muy utiles."
            + "\nY no te olvides del dinero";

    private Stage primaryStage;
    private StackPane pBase;

    public ControladorMain(Stage primaryStage) {
        this.cJugador = new ControladorJugador();
        this.cInicio = new ControladorInicio();
        this.cElegirNombre = new ControladorElegirNombre();
        this.cActo1 = new ControladorActo1();
        this.cItems = new ControladorEleccionItems();

        this.primaryStage = primaryStage;
        this.pBase = new StackPane();
    }

    public void iniciar() {
        //Configuramos la pantalla: tamaño, padding, color, etc
        configurarPantalla();

        //Configuramos los eventos que pueden suceder en el juego
        configuracionEventosInicio();

        //Comenzamos el juego
        mostrarInicio();
    }

    private void configurarPantalla() {

        pBase.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pBase.getChildren().add(cInicio.getVistaInicio().getContenedorJuego());
        pBase.setPadding(new Insets(15, 15, 15, 15));

        Scene scene = new Scene(pBase, 850, 600);
        scene.getStylesheets().add(getClass().getResource("/estilos/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Aventura de Fin de Semana");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void configuracionEventosInicio() {
        //  Cambiar inicio a elegir nombre
        cInicio.getVistaInicio().getBtnInicio().setOnAction(event -> mostrarElegirNombre());

    }

    private void configuracionEventosActo1() {
        // Cargar evento de continuar juego
        cActo1.getVistaActo1().getBtnContinuar().setOnAction(event -> mostrarEleccionItems());

    }

    private void configuracionEventosElegirNombre() {
        //  Boton Volver al Inicio en pantalla elegir nombre
        cElegirNombre.getVistaElegirNombre().getBtnVolverInicio().setOnAction(event -> mostrarInicio());

        //  Boton aceptar obtener nombre jugador
        cElegirNombre.getVistaElegirNombre().getBtnAceptarNombre().setOnAction(event -> aceptarNombre());

        //  Boton continuar juego
        cElegirNombre.getVistaElegirNombre().getBtnContinuarJuego().setOnAction(event -> mostrarActo1());

    }

    //  Inicio
    private void mostrarInicio() {
        //  Limpiamos Stage y añadimos la vista inicio que nos proporciona 
        //  el controlador de inicio
        pBase.getChildren().clear();
        pBase.getChildren().add(cInicio.getVistaInicio().getContenedorJuego());
    }

    //  Elegir nombre
    private void mostrarElegirNombre() {
        //  Limpiamos Stage y añadimos la vista elegirNombre que nos proporciona 
        //  el controlador de elegirNombre
        pBase.getChildren().clear();
        pBase.getChildren().add(cElegirNombre.getVistaElegirNombre().getContenedorJuego());
        configuracionEventosElegirNombre();
    }

    //  Aceptar Nombre
    public void aceptarNombre() {
        // Aquí puedes agregar la lógica para procesar el nombre ingresado
        String nombreJugador = cElegirNombre.getVistaElegirNombre().getNombreJugador().getText();

        cJugador.setNombre(nombreJugador);
        showInformationAlert("¡Nombre ingresado correctamente!");
    }

    //Acto 1
    private void mostrarActo1() {

        showInformationAlert(instruccionesHistoria);

        if (cJugador.getNombre() != null) {
            this.cActo1 = new ControladorActo1();
            pBase.getChildren().clear();
            pBase.getChildren().add(cActo1.getContenedorJuego());
            configuracionEventosActo1();

            // Habilitar el botón de continuar
            cActo1.getVistaActo1().getBtnContinuar().setDisable(false);
        } else {
            showInformationAlert("¡Cuidado!¡No nos has dicho tu nombre!");

        }

    }

    //  Eleccion Items
    private void mostrarEleccionItems() {

        showInformationAlert(instruccionesEleccionItems);

        pBase.getChildren().clear();
        pBase.getChildren().add(cItems.getVistaItems().getContenedorJuego());

    }

    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();

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
