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
import vista.VistaMenuSierraNevada;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class ControladorMenuSierraNevada {

    private VistaMenuSierraNevada vMenu;
    private Pane contenedorJuego;

    private Image imgPersonaje;
    private ImageView personaje;
    private Label dialogo;

    private ConversacionesSierraNevada cSierraNevada;
    private StackPane pausaDialogo;
    private int contDialogo = 0;
    private int contPose = 0;

    private StackPane cRestaurante;
    private StackPane cPaseo;
    private StackPane cViaje;
    private StackPane cActual;

    private ImageView imgRestaurante;
    private ImageView imgPaseo;
    private ImageView imgViaje;

    private Label textRest;
    private Label textPaseo;
    private Label textViaje;

    private ContextMenu menuAcciones;
    private MenuItem entrar;
    private MenuItem interactuar;
    private MenuItem salir;

    private int seleccion = 0;

    public ControladorMenuSierraNevada() {
        vMenu = new VistaMenuSierraNevada();

        inicializarComponentes();
        iniciarEventos();
    }

    private void inicializarComponentes() {

        this.contenedorJuego = vMenu.getContenedorJuego();

        this.personaje = vMenu.getPersonaje();
        this.dialogo = vMenu.getDialogo();

        this.cRestaurante = vMenu.getcRestaurante();
        this.cPaseo = vMenu.getcPaseo();
        this.cViaje = vMenu.getcViajeTrineo();

        this.imgRestaurante = vMenu.getRestaurante();
        this.imgPaseo = vMenu.getPaseo();
        this.imgViaje = vMenu.getViajeTrineo();

        this.textRest = vMenu.getTextRest();
        this.textPaseo = vMenu.getTextPaseo();
        this.textViaje = vMenu.getTextViaje();

        this.menuAcciones = vMenu.getMenuAcciones();
        this.entrar = vMenu.getEntrar();
        this.interactuar = vMenu.getInfo();
        this.salir = vMenu.getSalir();

        this.cSierraNevada = new ConversacionesSierraNevada();
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

    protected int actividadSeleccionada() {

        return seleccion;
    }

    private void mostrarDialogo(StackPane cActividad) {
        String texto;
        if (cActividad == cRestaurante) {
            contDialogo++;

            texto = cSierraNevada.obtenerInteraccionPaseo(contDialogo);
            dialogoActividad(texto);
        }

        if (cActividad == cPaseo) {
            contDialogo++;

            texto = cSierraNevada.obtenerInteraccionPaseo(contDialogo);
            dialogoActividad(texto);
        }

        if (cActividad == cViaje) {
            contDialogo++;

            texto = cSierraNevada.obtenerInteraccionViajeTrineo(contDialogo);
            dialogoActividad(texto);
        }
    }

    private void mostrarPose(StackPane cActividad) {
        String pose;
        if (cActividad == cRestaurante) {
            contPose++;

            pose = cSierraNevada.obtenerPoseInteraccionRestaurante(contPose);
            poseDialogoActividad(pose);
        }

        if (cActividad == cPaseo) {
            contPose++;

            pose = cSierraNevada.obtenerPoseInteraccionPaseo(contPose);
            poseDialogoActividad(pose);
        }

        if (cActividad == cViaje) {
            contPose++;

            pose = cSierraNevada.obtenerPoseInteraccionViajeTrineo(contPose);
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

        if (cImagen == cViaje) {
            dialogo.setText("Un puesto de venta de tickets para trineos "
                    + " ojala todos se hayan traido su trineo. ¿Me habré traido yo el mio?");

        }

    }

    private void asignarMenuContextual() {
        asignarMenu(cRestaurante);
        asignarMenu(cPaseo);
        asignarMenu(cViaje);
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
        cViaje.setOnMouseExited(event -> {
            restablecerTamañoImagen(imgViaje);
            cViaje.setCursor(Cursor.DEFAULT);
            textViaje.setVisible(false);
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

        cViaje.setOnMouseEntered(event -> {
            aumentarTamañoImagen(imgViaje);
            cViaje.setCursor(Cursor.HAND);
            textViaje.setVisible(true);
            mostrarInformacionActividad(cViaje);

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

    public VistaMenuSierraNevada getvMenu() {
        return vMenu;
    }

    public Pane getContenedorJuego() {
        return contenedorJuego;
    }

}
