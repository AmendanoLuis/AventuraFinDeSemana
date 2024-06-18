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
public class MigueMedina implements Personaje {

    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public MigueMedina() {
        nombre = "Migue Medina";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "Y algunos incluso os habéis graduado.");
        dialogos.put("d2", "Yo a Sierra Nevada no podría ir pero de senderismo a los Cahorros voy de una.");
        dialogos.put("d3", "Venga pago yo y me hacéis bizum.");
        dialogos.put("d4", "Ya está pagado.");
        dialogos.put("d5", "Nos vemos.");
        dialogos.put("d6", "Venga pago yo y me hacéis bizum.");
        dialogos.put("d7", "Ya está pagado.");
        dialogos.put("d8", "¡Nos vemooos.");
        dialogos.put("d9", "Pues ya se va haciendo tarde, yo creo que deberíamos salir ya.");
        dialogos.put("d10", "Estoy bien, he ido antes en casa.");

    }

    private void cargarPoses() {
        poses.put("p1", "resources/migueTriste.png");
        poses.put("p2", "resources/miguePensante.png");
        poses.put("p3", "resources/migueCelebracion.png");
        poses.put("p4", "resources/migueNormal.png");

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
