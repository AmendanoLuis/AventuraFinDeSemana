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
import vista.VistaMinijuego;

/**
 *
 * @author Luis
 */
@Data
public class ControladorMinijuego {

    private VistaMinijuego vistaM;

    private StackPane coche;
    private ImageView rueda1;
    private ImageView rueda2;

    private Image imgPersonajeP1;
    private Image imgPersonajeP2;

    private ImageView personaje;
    private Label dialogo;
    private Button btnParar;

    private Transition transicionCoche;

    private static int POSICION_TERMINAR_ANIMACION = 730;

    private static double POSICION_PARADA_1 = 200;
    private static double POSICION_PARADA_2 = 450;
    private static double POSICION_AVISO_PARADA_1 = 150;
    private static double POSICION_AVISO_PARADA_2 = 300;
    private static final int DURACION_ANIMACION = 20;
    private static final int DURACION_PAUSA_PARADAS = 5;

    private int countParadas;
    private boolean mostrarAviso1 = false;
    private boolean mostrarAviso2 = false;

    private boolean parada1 = false;
    private boolean parada2 = false;

    private boolean juegoPerdido = true;
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

        coche = vistaM.getCCoche();
        rueda1 = vistaM.getRueda1();
        rueda2 = vistaM.getRueda2();

        personaje = vistaM.getPersonaje();
        dialogo = vistaM.getDialogo();
        btnParar = vistaM.getBtnParar();

    }

    public void cambiarImagenAlerta() {

        Jugador jugador = Jugador.getInstanciaJugador();

        if (jugador.isCahorros()) {
            alertaJuegoGanado.cambiarImagenAlerta(vistaM.getImgJuegoGanadoCh());
            alertaJuegoPerdido.cambiarImagenAlerta(vistaM.getImgJuegoPerdidoCh());
            alertaPausa.cambiarImagenAlerta(vistaM.getImgPausaCh());

        } else if (jugador.isSierraNevada()) {
            alertaJuegoGanado.cambiarImagenAlerta(vistaM.getImgJuegoGanadoSn());
            alertaJuegoPerdido.cambiarImagenAlerta(vistaM.getImgJuegoPerdidoSn());
            alertaPausa.cambiarImagenAlerta(vistaM.getImgPausaSn());
        }
    }

    private void cargarAlertas() {

        imgGanado = null;
        imgPerdido = null;
        imgPausa = null;

        alertaJuegoGanado = new Alerta(imgGanado, 200, 300, "Vamos");
        alertaJuegoPerdido = new Alerta(imgPerdido, 100, 100, "Reiniciar...");
        alertaPausa = new Alerta(imgPausa, 180, 250, "Aceptar");

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
                Duration.seconds(DURACION_ANIMACION), coche, rueda1, rueda2,
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

            } else if (juegoPerdido) {
                terminarJuegoPerdido();
            }

        });

        transicionCoche.play();

    }

    private void cargarEventoReiniciarJuegoBotonAlerta() {
        alertaJuegoPerdido.getBtnAlerta().setOnAction(event -> {
            reiniciarJuego();
            alertaJuegoPerdido.retirarAlerta();

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
            comprobarEstadoJuego();
            comprobarEstadoJuegoParada2();

            if (!parada2 && !parada1) {

            }

            if (parada1) {
                pausarJuego();
                alertaPausa.mostrarAlertaConImagen("Esperando...");
            } else if (mostrarAviso1 && !parada1) {
                terminarJuegoPerdido();
            }

            if (parada2) {
                pausarJuego();
                alertaPausa.mostrarAlertaConImagen("Bueno, quien se cuenta algo :)");
            } else if (!parada2 && mostrarAviso2) {
                terminarJuegoPerdido();
            }

        });
    }

    private void comprobarEstadoJuego() {

        double posicionCoche = coche.getTranslateX();

        if (parada1 && parada2) {
            juegoGanado = true;

        }

        if (posicionCoche > POSICION_AVISO_PARADA_1
                && posicionCoche < POSICION_PARADA_1) {
            if (transicionCoche.getStatus() != Animation.Status.PAUSED
                    && countParadas == 0) {

                countParadas = 1;
                POSICION_PARADA_1 = 800;

                parada1 = true;
            }
        }

    }

    private void comprobarEstadoJuegoParada2() {

        double posicionCoche = coche.getTranslateX();

        if (parada1 && parada2) {
            juegoGanado = true;

        }

        if (parada1) {

            if (posicionCoche > POSICION_AVISO_PARADA_2
                    && posicionCoche < POSICION_PARADA_2) {

                mostrarAviso1 = false;

                if (transicionCoche.getStatus() != Animation.Status.PAUSED
                        && countParadas == 1) {

                    countParadas = 2;
                    POSICION_PARADA_2 = 800;

                    parada2 = true;
                    juegoPerdido = false;

                }

            }

        }

    }

    private void terminarJuegoGanado() {
        alertaJuegoGanado.mostrarAlertaConImagen("¡Has llegado con el coche limpio!");
    }

    private void terminarJuegoPerdido() {
        alertaJuegoPerdido.mostrarAlertaConImagen("Te han llenado el coche de meao");
        parada1 = false;
        parada2 = false;
        cargarEventoReiniciarJuegoBotonAlerta();
    }

    private void reiniciarJuego() {

        transicionCoche.stop();
        transicionCoche = new TransicionMinijuego(
                Duration.seconds(DURACION_ANIMACION), coche, rueda1, rueda2, 0,
                POSICION_TERMINAR_ANIMACION, this);

        juegoPerdido = true;
        juegoGanado = false;

        parada1 = false;
        parada2 = false;
        countParadas = 0;
        POSICION_PARADA_1 = 265;
        POSICION_PARADA_2 = 530;

        transicionCoche.play();

    }

    private void pausarJuego() {
        imgPersonajeP2 = vistaM.getImgPersonajeP2();

        transicionCoche.pause();
        reproducirSonido();

        PauseTransition pause = new PauseTransition(Duration.seconds(DURACION_PAUSA_PARADAS));
        pause.setOnFinished(event -> {
            personaje.setImage(imgPersonajeP2);
            dialogo.setText("Gracias por esperar chicoss :)");

            this.alertaPausa.retirarAlerta();

            transicionCoche.play();

        });

        pause.play();
    }

    protected void mostrarAviso(double posicionCoche) {
        imgPersonajeP1 = vistaM.getImgPersonajeP1();

        if (posicionCoche >= POSICION_AVISO_PARADA_1 && posicionCoche <= 200) {
            personaje.setImage(imgPersonajeP1);
            dialogo.setText("Ay, parad que me meo por favor.");
            mostrarAviso1 = true;

        } else if (posicionCoche >= POSICION_AVISO_PARADA_2 && posicionCoche <= 450) {
            personaje.setImage(imgPersonajeP1);
            dialogo.setText("¡¡Para el coche!! Lo siento, tengo que ir al baño otra vez.");
            mostrarAviso2 = true;

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
