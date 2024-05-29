/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Luis
 */
public class OldActo1 {

    //PanelHistoria
    private Label dialogo = new Label();
    private Image imagen1;
    private ImageView imagenesHistoria;
    
    //Contenedor Juego
    private VBox contenedorJuego;

    //PanelEleccion
    private Button btnSierraNevada;
    private Button btnCahorros;
    private HBox contenedorBotonEleccion;

    private Label pregunta;
    private VBox contenedorEleccion;

    public OldActo1() {

        establecerPanel();
        
    }

    private void establecerPanel() {

        contenedorJuego = new VBox(20);
        contenedorJuego.setPadding(new Insets(-20, 10, 10, 10));
        contenedorJuego.setAlignment(Pos.CENTER);
        establecerPanelHistoria();
        
        // Manejar clics en el contenedor para alternar entre los paneles
        contenedorJuego.setOnMouseClicked((MouseEvent event) -> {
            alternarPanel();
        });

    }

    public void establecerPanelHistoria() {
        imagen1 = new Image("images/panelActo1/Pista_Esqui.jpg");
        imagenesHistoria = new ImageView(imagen1);
        contenedorJuego.getChildren().addAll(imagenesHistoria, dialogo);

    }

    public void establecerPanelEleccion() {

        
        
        btnSierraNevada = new Button("Sierra Nevada");
        btnCahorros = new Button("Soportujar");

        contenedorBotonEleccion.getChildren().addAll(btnSierraNevada, btnCahorros);

        pregunta = new Label("¿Donde quieres ir?");

        contenedorEleccion.getChildren().addAll(pregunta, contenedorBotonEleccion);

        contenedorJuego.getChildren().add(contenedorEleccion);

    }

     public void alternarPanel() {
        if (true) {
            establecerPanelHistoria();
        } else {
            establecerPanelEleccion();
        }
    }

    public Button getBtnEleccion1() {
        return btnSierraNevada;
    }

    public Button getBtnEleccion2() {
        return btnCahorros;
    }

    public VBox getContenedorJuego() {
        return contenedorJuego;
    }
     
    
     
    
}
