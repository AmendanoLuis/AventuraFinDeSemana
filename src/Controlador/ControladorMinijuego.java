/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import lombok.*;
import modelo.Alerta;
import modelo.Jugador;
import personajes.MariaTrueno;
import vista.VistaMinijuego;

/**
 *
 * @author Luis
 */
@Data
public class ControladorMinijuego {

    private VistaMinijuego vistaM;

    private ImageView coche;
    private Image imgPersonajeP1;
    private Image imgPersonajeP2;

    private ImageView personaje;
    private Label dialogo;
    private Button btnParar;

    private Transition transicionCoche;

    private static final int POSICION_TERMINAR_ANIMACION = 730;
    private static int POSICION_LIMITE_COCHE_1 = 400;

    private static int POSICION_LIMITE_COCHE_2 = 720;

    private static double POSICION_PARADA_1 = 265;
    private static double POSICION_AVISO_PARADA_1 = 165;

    private static double POSICION_PARADA_2 = 530;
    private static double POSICION_AVISO_PARADA_2 = 430;

    private boolean parada1 = false;
    private boolean parada2 = false;

    private boolean juegoPerdido = false;
    private boolean juegoGanado = false;

    private Pane contenedorJuego;
    private MediaPlayer reproductor;

    private Alerta alertaJuegoGanado;
    private Image imgGanado;

    private Alerta alertaJuegoPerdido;
    private Image imgPerdido;

    private Alerta alertaPausa;
    private Image imgPausa;

    public ControladorMinijuego() {
        vistaM = new VistaMinijuego();

        inicializarComponentes();
        cargarAlertas();

    }

    private void inicializarComponentes() {

        contenedorJuego = vistaM.getContenedorJuego();

        cargarImagenesAlerta();

        coche = vistaM.getCoche();

        personaje = vistaM.getPersonaje();
        dialogo = vistaM.getDialogo();
        btnParar = vistaM.getBtnParar();

    }

    protected void cargarImagenesAlerta() {

        imgGanado = vistaM.getImgJuegoGanadoSn();
        imgPerdido = vistaM.getImgJuegoPerdidoSn();
        imgPausa = vistaM.getImgPausaSn();
        imgGanado = vistaM.getImgJuegoGanadoCh();
        imgPerdido = vistaM.getImgJuegoPerdidoCh();
        imgPausa = vistaM.getImgPausaCh();

    }

    private void cargarAlertas() {
        alertaJuegoGanado = new Alerta(imgGanado, 200, 300);
        alertaJuegoPerdido = new Alerta(imgPerdido, 100, 100);
        alertaPausa = new Alerta(imgPausa, 200, 300);

        contenedorJuego.getChildren().addAll(
                alertaJuegoGanado.getAlerta(),
                alertaJuegoPerdido.getAlerta(),
                alertaPausa.getAlerta());

        alertaJuegoGanado.getAlerta().setLayoutX(290);
        alertaJuegoPerdido.getAlerta().setLayoutX(290);
        alertaPausa.getAlerta().setLayoutX(290);

        alertaJuegoGanado.getAlerta().setLayoutY(50);
        alertaJuegoPerdido.getAlerta().setLayoutY(50);
        alertaPausa.getAlerta().setLayoutY(50);

    }

    protected void iniciarEventos() {
        cargarSonidos();
        movimientoCoche();
        iniciarTransicion();
        eventosBoton();
    }

    private void movimientoCoche() {

        transicionCoche = new TransicionMinijuego(
                Duration.seconds(20), coche,
                0, POSICION_TERMINAR_ANIMACION, this);

    }

    private void iniciarTransicion() {
        personaje.setImage(null);
        dialogo.setText(" ");

        transicionCoche.setOnFinished(event -> {

            comprobarEstadoJuego();

            if (juegoGanado) {
                terminarJuegoGanado();

                vistaM.getCBotonTerminarMinijuego().setVisible(true);
                vistaM.getCBotonTerminarMinijuego().toFront();

            }

            if (juegoPerdido) {
                alertaJuegoPerdido.mostrarAlertaConImagen("JUEGO PERDIDO 2");

                eventoReiniciarConBotonAlerta();

            }

        });

        transicionCoche.play();

    }

    private void eventoReiniciarConBotonAlerta() {
        alertaJuegoPerdido.getBtnAlerta().setOnAction(event -> {
            alertaJuegoPerdido.retirarAlerta();
            reiniciarJuego();

        });
    }

    private void eventosBoton() {

        btnParar.setOnMouseEntered(event -> {
            btnParar.setCursor(Cursor.HAND);
        });

        btnParar.setOnMouseExited(event -> {
            btnParar.setCursor(Cursor.DEFAULT);
        });
        btnParar.setOnAction(event -> {
            pararCoche();
            if (parada1) {
                pausarJuego();
                POSICION_PARADA_1 = 800;
                POSICION_LIMITE_COCHE_1 = 800;
                alertaPausa.mostrarAlertaConImagen("PAUSA PARADA 1");

            }

            if (parada2) {
                pausarJuego();
                POSICION_PARADA_2 = 800;

                alertaPausa.mostrarAlertaConImagen("PAUSA PARADA 2");

            }

        });
    }

    private void pararCoche() {
        comprobarEstadoJuego();

    }

    private void comprobarEstadoJuego() {

        double posicionCoche = coche.getTranslateX();

        if (posicionCoche > POSICION_PARADA_1
                && posicionCoche < POSICION_LIMITE_COCHE_1) {

            reiniciarJuego();

        } else if (posicionCoche >= POSICION_AVISO_PARADA_1
                && posicionCoche < POSICION_PARADA_1
                && transicionCoche.getStatus() != Animation.Status.PAUSED) {

            parada1 = true;
        }

        if (parada1) {

            if (posicionCoche > POSICION_PARADA_2
                    && posicionCoche < POSICION_LIMITE_COCHE_2) {

                reiniciarJuego();

            } else if (posicionCoche >= POSICION_AVISO_PARADA_2
                    && posicionCoche <= POSICION_PARADA_2
                    && transicionCoche.getStatus() != Animation.Status.PAUSED) {

                juegoPerdido = false;
                parada2 = true;
                juegoGanado = true;

            }
        }

    }

    private void terminarJuegoGanado() {
        alertaJuegoGanado.mostrarAlertaConImagen("¡Has llegado con el coche limpio!");
    }

    private void reiniciarJuego() {
        if (juegoPerdido) {
            transicionCoche.stop();
            transicionCoche = new TransicionMinijuego(
                    Duration.seconds(20), coche, 0,
                    POSICION_TERMINAR_ANIMACION, this);

            juegoPerdido = true;
            juegoGanado = false;

            parada1 = false;
            parada2 = false;

            POSICION_PARADA_1 = 265;
            POSICION_PARADA_2 = 530;

            POSICION_LIMITE_COCHE_1 = 400;

            POSICION_LIMITE_COCHE_2 = 720;

            transicionCoche.play();
        }

    }

    private void pausarJuego() {
        imgPersonajeP2 = vistaM.getImgPersonajeP2();

        transicionCoche.pause();
        reproducirSonido();

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            personaje.setImage(imgPersonajeP2);
            dialogo.setText("Parad porfa, disculpad enseguida vengo.");

            transicionCoche.play();

            this.alertaPausa.retirarAlerta();
        });

        pause.play();
    }

    protected void mostrarAviso(double posicionCoche) {
        imgPersonajeP1 = vistaM.getImgPersonajeP1();

        if (posicionCoche >= POSICION_AVISO_PARADA_1 && posicionCoche <= 265) {
            personaje.setImage(imgPersonajeP1);
            dialogo.setText("Ay, parad que me meo por favor.");
            comprobarEstadoJuego();

        } else if (posicionCoche >= POSICION_AVISO_PARADA_2 && posicionCoche <= 530) {
            personaje.setImage(imgPersonajeP1);
            dialogo.setText("¡¡Para el coche!! Lo siento tengo que ir al baño otra vez.");
            comprobarEstadoJuego();

        } else {
            personaje.setImage(null);
            dialogo.setText(" ");
        }

    }

    private void cargarSonidos() {

        try {
            File soundFile = new File("src/resources/pararCoche.mp3");
            if (soundFile.exists()) {
                Media sound = new Media(soundFile.toURI().toString());
                reproductor = new MediaPlayer(sound);
            } else {
                System.err.println("Archivo de sonido no encontrado: " + soundFile.getPath());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void reproducirSonido() {

        if (reproductor != null) {
            reproductor.stop();
        }
        reproductor.play();
    }

}
