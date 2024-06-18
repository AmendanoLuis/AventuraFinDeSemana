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
public class KarlMarx implements Personaje {

    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public KarlMarx() {
        nombre = "Karl Marx";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "... y es por eso que es mejor el barsa que el Madrid ");
        dialogos.put("d2", "Gracias amigo.");
        dialogos.put("d3", "Yo preferiría ir a Sierra Nevada la verdad, que hace tiempo que no voy a la nieve.");
        dialogos.put("d4", "VAMOOOS YEAHHH");
        dialogos.put("d5", "Guay, ahora te hacemos bizum. Vámonos a casa que hay que descansar.");
        dialogos.put("d6", "Venga vamos, adiós migue. Adiós Einebel.");
        dialogos.put("d7", "Creo que Einebel se ha dormido porque no me contesta las llamadas.");
        dialogos.put("d8", "Si, porque si no no vamos a disfrutar de Sierra Nevada.");
        dialogos.put("d9", "Yo igual, me voy subiendo.");
        dialogos.put("d10", "Guay, ahora te hacemos bizum. Vámonos a casa que hay que descansaaar.");
        dialogos.put("d11", "Venga vamos, adiós Migue. Adiós Einebel.");

    }

    private void cargarPoses() {
        poses.put("p1", "resources/carlPensante.png");
        poses.put("p2", "resources/carlNormal.png");
        poses.put("p3", "resources/carlVacilon.png");
        poses.put("p4", "resources/carlJocoso.png");

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
