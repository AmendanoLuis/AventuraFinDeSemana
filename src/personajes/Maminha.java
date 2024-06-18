/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Personaje;

/**
 *
 * @author Luis
 */
public class Maminha implements Personaje {

    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public Maminha() {
        this.nombre = "Maminha";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "Puta la barsa !!!");
        dialogos.put("d2", "Estaba hecho un zombie el chaval.");
        dialogos.put("d3", "Gracias bebes.");
        dialogos.put("d4", "Heller y yo nos adaptamos.");
        dialogos.put("d5", "Decidido pues, vamos a pagar y nos vamos que mañana hay que despertar temprano para ir.");
        dialogos.put("d6", "Adios chavalada.");
        dialogos.put("d7", "Hola, ¿has dormido bien? Espero que si porque hoy nos espera un gran día.");
        dialogos.put("d8", "Yo aprovecharé para dormir de camino . ¿Alguien quiere ir al baño antes?");
        dialogos.put("d9", "Decidido pues, vamos a pagar y nos vamos que mañana hay que despertar temprano para ir. ");
        dialogos.put("d10", "Adiós chavalee.");
        dialogos.put("d11", "Hola *nombre de jugador *, ¿Has dormido bien? Espero que si porque hoy nos espera un gran día.");
        dialogos.put("d12", "Yo aprovecharé para dormir de camino. ¿Alguien quiere ir al baño antes?");
    }

    private void cargarPoses() {
        poses.put("p1", "resources/maminhaVacilona.png");
        poses.put("p2", "resources/maminhaAgresiva.png");
        poses.put("p3", "resources/maminhaJocosa.png");
        poses.put("p4", "resources/maminhaNormal.png");

    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public String obtenerDialogo(String clave) {
        return dialogos.get(clave);

    }

    @Override
    public String obtenerPose(String clave) {
        return poses.get(clave);

    }

    @Override
    public void generarDinero() {
        Random r = new Random();
        double min = 60;
        double max = 100;
        double dineroG = min + (max - min) * r.nextDouble();
        this.dinero = dineroG;
    }

    @Override
    public double obtenerDinero() {
        return this.dinero;

    }

}
