/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import modelo.Jugador;
import personajes.MariaTrueno;
import vista.VistaMinijuego;

/**
 *
 * @author Luis
 */
public class ControladorMinijuego {

    private VistaMinijuego vistaM;
    private ImageView coche;
    private Image imgPersonajeP1;
    private Image imgPersonajeP2;

    private ImageView personaje;
    private Label dialogo;
    private Button btnParar;

    private Transition transicionCoche;
    private double posicionXParada1 = 265;
    private double posicionAvisoParada1 = 165;
    private double posicionXParada2 = 530;
    private double posicionAvisoParada2 = 430;

    private boolean parada1 = false;
    private boolean parada2 = false;

    private boolean juegoPerdido;
    private boolean juegoGanado;

    private Pane contenedorJuego;
    private MediaPlayer reproductor;

    public ControladorMinijuego() {
        vistaM = new VistaMinijuego();
        inicializarComponentes();

    }

    private void inicializarComponentes() {

        contenedorJuego = vistaM.getContenedorJuego();


        //  Coche
        coche = vistaM.getCoche();

        //  Nodos panel control juego
        personaje = vistaM.getPersonaje();
        dialogo = vistaM.getDialogo();
        btnParar = vistaM.getBtnParar();

    }

    protected void iniciarEventos() {
        cargarSonidos();
        movimientoCoche();
        iniciarTransicion();
        eventosBoton();
    }

    private void movimientoCoche() {

        transicionCoche = new TransicionMinijuego(Duration.seconds(40), coche, 0, 800, this);

    }

    private void iniciarTransicion() {
        personaje.setImage(null);
        dialogo.setText(" ");
        transicionCoche.play();

    }

    private void eventosBoton() {
        btnParar.setOnAction(event -> pararCoche());
    }

    private void pararCoche() {
        comprobarEstadoJuego();

    }

    private void comprobarEstadoJuego() {

        juegoPerdido = false;
        juegoGanado = false;
        double posicionCoche = coche.getTranslateX();

        if (!juegoPerdido && !juegoGanado) {

            if (!parada1) {

                if (posicionCoche > posicionXParada1) {
                    juegoPerdido = true;
                    alertaInformacion("¡Se han meado encima!");
                    reiniciarJuego();

                } else if (transicionCoche.getStatus() != Animation.Status.PAUSED) {
                    parada1 = true;
                    pausarJuego();
                }

            } else if (!parada2) {
                if (posicionCoche > posicionXParada2) {
                    juegoPerdido = true;
                    alertaInformacion("¡Se han meado encima!");
                    reiniciarJuego();

                } else if (transicionCoche.getStatus() != Animation.Status.PAUSED) {
                    juegoGanado = true;
                    parada2 = true;
                    pausarJuego();
                }
            }
        }

        if (juegoGanado) {
            terminarJuego();
        }

    }

    private void reiniciarJuego() {
        transicionCoche.stop();
        transicionCoche = new TransicionMinijuego(Duration.seconds(40), coche, 0, 800, this);
        juegoPerdido = false;
        juegoGanado = false;
        transicionCoche.play();
    }

    private void pausarJuego() {
        imgPersonajeP2 = vistaM.getImgPersonajeP2();

        transicionCoche.pause();

        PauseTransition pause = new PauseTransition(Duration.seconds(15));
        pause.setOnFinished(event -> {
            personaje.setImage(imgPersonajeP2);
            dialogo.setText("Lo siento, se me olvidó ir por la mañana.");
            if (parada1) {
                posicionAvisoParada1 = 850;

            }
            if (parada2) {
                posicionAvisoParada2 = 850;

            }
            transicionCoche.play();

        });
        personaje.setImage(imgPersonajeP2);
        dialogo.setText("Lo siento, se me olvidó ir por la mañana.");

        pause.play();
    }

    private void terminarJuego() {

        transicionCoche.setOnFinished(event -> alertaInformacion("¡Has Ganado!"));

    }

    protected void mostrarAviso(double posicionXCoche) {
        imgPersonajeP1 = vistaM.getImgPersonajeP1();

        if (posicionXCoche >= posicionAvisoParada1 && posicionXCoche <= posicionXParada1) {
            personaje.setImage(imgPersonajeP1);
            dialogo.setText("Ay, parad que me meo por favor.");
            reproducirSonido();
        } else if (posicionXCoche >= posicionAvisoParada2 && posicionXCoche <= posicionXParada2) {
            personaje.setImage(imgPersonajeP1);
            dialogo.setText("¡¡Para el coche!! Lo siento tengo que ir al baño otra vez.");
            reproducirSonido();
        } else {
            personaje.setImage(null);
            dialogo.setText(" ");
        }
    }

    private void cargarSonidos() {
        // Asegúrate de que el archivo tenga la extensión correcta (por ejemplo, .mp3)

        try {
            File soundFile = new File("src/audio/pararCoche.mp3");
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

    private void alertaInformacion(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public Pane getContenedorJuego() {
        return contenedorJuego;
    }

    public VistaMinijuego getVistaM() {
        return vistaM;
    }

    public boolean isJuegoPerdido() {
        return juegoPerdido;
    }

    public boolean isJuegoGanado() {
        return juegoGanado;
    }
    
    

}
