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
public class CP_Salami implements Personaje {

    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public CP_Salami() {
        nombre = "C.P Salami";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "Ya ves, llevaba semanas sin dormir.");
        dialogos.put("d2", "¿Hacemos algo este Fin de Semana para celebrarlo?");
        dialogos.put("d3", "Ya ves, yo igual.");
        dialogos.put("d4", "MOGUISE BLAAAAH!!! ");
        dialogos.put("d5", "Esperad que voy con vosotros.");
        dialogos.put("d6", "Pues ya se va haciendo tarde, yo creo que deberíamos salir ya.");
        dialogos.put("d7", "Estoy bien, he ido antes en casa.");
        dialogos.put("d8", "Esperad que voy con vosotros.");

    }

    private void cargarPoses() {
        poses.put("p1", "resources/salamiNormal.png");
        poses.put("p2", "resources/salamiJocoso.png");
        poses.put("p3", "resources/salamiSorprendido.png");

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
