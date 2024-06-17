/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import modelo.ConversacionesSierraNevada;
import vista.VistaMenuCahorros;
import lombok.*;

/**
 *
 * @author user
 */

@Data
public class ControladorMenuCahorros {

    private VistaMenuCahorros vMenu;
    private Pane contenedorJuego;

    private Image imgPersonaje;
    private ImageView personaje;
    private Label dialogo;

    private ConversacionesSierraNevada cCahorros;
    private StackPane pausaDialogo;
    private int contDialogo = 0;
    private int contPose = 0;

    private StackPane cRestaurante;
    private StackPane cPaseo;
    private StackPane cActual;

    private ImageView imgRestaurante;
    private ImageView imgPaseo;

    private Label textRest;
    private Label textPaseo;

    private ContextMenu menuAcciones;
    private MenuItem entrar;
    private MenuItem interactuar;
    private MenuItem salir;

    public ControladorMenuCahorros() {
        vMenu = new VistaMenuCahorros();

        inicializarComponentes();
        iniciarEventos();
    }

    private void inicializarComponentes() {

        this.contenedorJuego = vMenu.getContenedorJuego();

        this.personaje = vMenu.getPersonaje();
        this.dialogo = vMenu.getDialogo();

        this.cRestaurante = vMenu.getCRestaurante();
        this.cPaseo = vMenu.getCPaseo();

        this.imgRestaurante = vMenu.getRestaurante();
        this.imgPaseo = vMenu.getPaseo();

        this.textRest = vMenu.getTextRest();
        this.textPaseo = vMenu.getTextPaseo();

        this.menuAcciones = vMenu.getMenuAcciones();
        this.entrar = vMenu.getEntrar();
        this.interactuar = vMenu.getInfo();
        this.salir = vMenu.getSalir();

        this.cCahorros = new ConversacionesSierraNevada();
        this.pausaDialogo = vMenu.getPausaDialogo();
    }

    private void iniciarEventos() {

        sobresaltarImagenes();

        restablecerImagenes();

        asignarMenuContextual();

        accionItemsMenu();
    }

    private void accionItemsMenu() {

        interactuar.setOnAction(event -> interaccionPersonajesActividad((StackPane) menuAcciones.getOwnerNode()));
        salir.setOnAction(event -> menuAcciones.hide());
    }

    private void interaccionPersonajesActividad(StackPane cActividad) {
        pausaDialogo.setVisible(true);
        pausaDialogo.toFront();
        pausaDialogo.setCursor(Cursor.HAND);

        cActual = cActividad;
        mostrarDialogo(cActividad);
        mostrarPose(cActividad);

        pausaDialogo.setOnMouseClicked(event -> {
            mostrarDialogo(cActividad);
            mostrarPose(cActividad);
        });

    }

    private void mostrarDialogo(StackPane cActividad) {
        String texto;
        if (cActividad == cRestaurante) {
            contDialogo++;

            texto = cCahorros.obtenerInteraccionPaseo(contDialogo);
            dialogoActividad(texto);
        }

        if (cActividad == cPaseo) {
            contDialogo++;

            texto = cCahorros.obtenerInteraccionPaseo(contDialogo);
            dialogoActividad(texto);
        }

    }

    private void mostrarPose(StackPane cActividad) {
        String pose;
        if (cActividad == cRestaurante) {
            contPose++;

            pose = cCahorros.obtenerPoseInteraccionRestaurante(contPose);
            poseDialogoActividad(pose);
        }

        if (cActividad == cPaseo) {
            contPose++;

            pose = cCahorros.obtenerPoseInteraccionPaseo(contPose);
            poseDialogoActividad(pose);
        }

    }

    private void dialogoActividad(String texto) {
        if (texto != null && !texto.isEmpty()) {
            dialogo.setText(texto);
        } else {
            dialogo.setText("");
            contDialogo = 0;
            pausaDialogo.setVisible(false);
            pausaDialogo.toBack();
        }
    }

    private void poseDialogoActividad(String pose) {
        try {

            if (pose == null) {
                Image imgNegro = new Image(vMenu.getPathImage());
                personaje.setImage(imgNegro);
                contPose = 0;

            } else {
                imgPersonaje = new Image(pose);
                personaje.setImage(imgPersonaje);

            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);

        } catch (NullPointerException ex) {
            System.out.println("Error: " + ex);

        }
    }

    private void mostrarInformacionActividad(StackPane cImagen) {
        if (cImagen == null) {
            return;
        }

        if (cImagen == cRestaurante) {
            dialogo.setText("Hoy podriamos comer algo fuera. Ese restaurante"
                    + " tiene buena pinta.");
        }

        if (cImagen == cPaseo) {
            dialogo.setText("Que buenas vistas se ven. Un paseo para "
                    + "disfrutar la naturaleza podria ser buena idea.");

        }

    }

    private void asignarMenuContextual() {
        asignarMenu(cRestaurante);
        asignarMenu(cPaseo);
    }

    private void restablecerImagenes() {

        cRestaurante.setOnMouseExited(event -> {
            restablecerTamañoImagen(imgRestaurante);
            cRestaurante.setCursor(Cursor.DEFAULT);
            textRest.setVisible(false);
            dialogo.setText("");
        });

        cPaseo.setOnMouseExited(event -> {
            aumentarTamañoImagen(imgPaseo);
            cPaseo.setCursor(Cursor.HAND);
            textPaseo.setVisible(false);
            dialogo.setText("");
        });

    }

    private void sobresaltarImagenes() {

        cRestaurante.setOnMouseEntered(event -> {
            aumentarTamañoImagen(imgRestaurante);
            cRestaurante.setCursor(Cursor.HAND);
            textRest.setVisible(true);
            mostrarInformacionActividad(cRestaurante);

        });
        cPaseo.setOnMouseEntered(event -> {
            restablecerTamañoImagen(imgPaseo);
            cPaseo.setCursor(Cursor.HAND);
            textPaseo.setVisible(true);
            mostrarInformacionActividad(cPaseo);

        });

    }

    private void aumentarTamañoImagen(ImageView imagen) {

        imagen.setFitHeight(imagen.getFitHeight() * 1.1);
        imagen.setFitWidth(imagen.getFitWidth() * 1.1);

        imagen.setLayoutX(imagen.getLayoutX() - (imagen.getFitWidth() * 1.1));
        imagen.setLayoutY(imagen.getLayoutY() - (imagen.getFitHeight() * 1.1));

    }

    private void restablecerTamañoImagen(ImageView imagen) {
        imagen.setFitHeight(imagen.getFitHeight() / 1.1);
        imagen.setFitWidth(imagen.getFitWidth() / 1.1);

        imagen.setLayoutX(imagen.getLayoutX() + (imagen.getFitWidth() * 0.05));
        imagen.setLayoutY(imagen.getLayoutY() + (imagen.getFitHeight() * 0.05));

    }

    private void asignarMenu(StackPane imagen) {
        imagen.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                menuAcciones.show(imagen, event.getScreenX(), event.getScreenY());
            }
        });
    }

}
