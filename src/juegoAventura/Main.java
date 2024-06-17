/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package juegoAventura;

import Controlador.ControladorMain;
import javafx.application.Application;

import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class Main extends Application {

    //Variables
    @Override
    public void start(Stage primaryStage) {
        ControladorMain controladorPrincipal = new ControladorMain(primaryStage);
        controladorPrincipal.iniciar();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}