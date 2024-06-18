/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import modelo.Personaje;

/**
 *
 * @author Luis
 */
public class EinalbelWardrobe implements Personaje {
 
    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public EinalbelWardrobe() {
        nombre = "Einalbel Wardrobe";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "Que alivio haber acabado ya de exámenes.");
        dialogos.put("d2", "Gracias Heller.");
        dialogos.put("d3", "Yo igual.");
        dialogos.put("d4", "Bueno, ¿pues quién paga?");
        dialogos.put("d5", "Es cierto, es mañana no me acordaba jsjs");
        dialogos.put("d6", "Adiós adiós .");
        dialogos.put("d7", "Bueno, ¿pues quién paga?");
        dialogos.put("d8", "Ay pero si es mañana no me acordaba jsjs");
        dialogos.put("d9", " Adiós adiós .");
        dialogos.put("d10", "Creo que Marx se ha dormido porque no me contesta las llamadas.");
        dialogos.put("d11", "Si, porque si no no vamos a disfrutar del senderismo que hay un puente con muy buenas vistas.");
        dialogos.put("d12", "Yo igual, me voy subiendo.");

    }

    private void cargarPoses() {
        poses.put("p1", "resources/einebelNormal.png");
        poses.put("p2", "resources/einebelVacilona.png");
        poses.put("p3", "resources/einebelPensante.png");
        poses.put("p4", "resources/einebelEsperando.png");

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
