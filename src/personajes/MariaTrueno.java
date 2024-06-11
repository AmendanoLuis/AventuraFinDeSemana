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
public class MariaTrueno implements Personaje {
    
    
    private double dinero;

    private Map<String, String> poses;

    private String nombre;

    private Map<String, String> dialogos;

    public MariaTrueno() {
        nombre = "Maria Trueno";
        this.dialogos = new HashMap<>();
        this.poses = new HashMap<>();
        cargarDialogos();
        cargarPoses();
        generarDinero();

    }

    private void cargarDialogos() {
        dialogos.put("d1", "¡Hola! Soy Maria Trueno");
        dialogos.put("d2", "¿Como estan muchachos?");
        dialogos.put("d3", "Muy bueno.");
        dialogos.put("d4", "*saca el movil* Ala, esto pa proyectos 9.000");
        dialogos.put("d5", "¡Con dios!");

    }

    private void cargarPoses() {
        poses.put("p1", "images/truenoEmote/truenoNormal.png");
        poses.put("p2", "images/truenoEmote/truenoPensante.png");
        poses.put("p3", "images/truenoEmote/truenoVacilon.png");

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
