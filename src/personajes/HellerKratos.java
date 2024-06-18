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
public class HellerKratos implements Personaje {

    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public HellerKratos() {
        nombre = "Heller Kratos";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "Y puta la confederación hidrográfica del Guadiana !!!");
        dialogos.put("d2", "¡¡¡Felicidades a los graduados!!! Maminha, Marx y Einabel.");
        dialogos.put("d3", "De senderismo podemos ir a los Cahorros.");
        dialogos.put("d4", "Pues decide tu, ¿dónde quieres ir?");
        dialogos.put("d5", "Pues a Sierra Nevada vamos.");
        dialogos.put("d6", "Sip vámonos, nos vemos mañana chicos.");
        dialogos.put("d7", "Hey, buenos días, bueno ya somos seis. ¿Viene alguien más? ");
        dialogos.put("d8", "Bueno pues nos vamos ya, subir todos al coche.");
        dialogos.put("d9", "Bueno pues guay, partamos entonces.");
        dialogos.put("d10", "Pues a los Cahorros vamos.");
        dialogos.put("d11", " Sí vámonos, nos vemos mañana chicos.");
        dialogos.put("d12", "Hey, buenos días, bueno ya somos seis. ¿Viene alguien más? ");
        dialogos.put("d13", "Bueno pues nos vamos ya, subir todos al coche.");
        dialogos.put("d14", "Bueno pues guay, partamos entonces.");

    }

    private void cargarPoses() {
        poses.put("p1", "resources/poseHellerNormal.png");
        poses.put("p2", "resources/poseHellerPensante.png");

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
