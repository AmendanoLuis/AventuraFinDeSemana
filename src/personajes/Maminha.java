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
        dialogos.put("d1", "¡Hola! Soy Maminha.");
        dialogos.put("d2", "Que tal peleles");
        dialogos.put("d3", "Vamos espabilad chavales.");
        dialogos.put("d4", "...");
        dialogos.put("d5", "Adios chavalada.");
    }

    private void cargarPoses() {
        poses.put("p1", "images/maminhaEmote/poseMaminhaNormal.png");
        poses.put("p2", "images/maminhaEmote/poseMaminhaPensante.png");

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
