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
        dialogos.put("d1", "Bueno, que felices todos después de terminar ya.");
        dialogos.put("d2", "Sii, podemos ir a hacer senderismo o a la nieve en Sierra Nevada");
        dialogos.put("d3", "Por mi donde digáis.");
        dialogos.put("d4", "Aiba si es mañana yaa, que guay ,vamos a la nieve.");
        dialogos.put("d5", "Bueno vámonos ya que verás que alguno se duerme jsjs");
        dialogos.put("d6", "Migue tampoco me contesta. ");
        dialogos.put("d7", "Yo estoy bien.");
        dialogos.put("d8", "Aiba si es mañana yaa, que guayy , me han dicho que es precioso.");
        dialogos.put("d9", "Bueno vámonos ya que verás que alguno se duerme jsjs");
        dialogos.put("d10", "Salami tampoco me contesta. ");
        dialogos.put("d11", "Yo estoy bien.");

    }

    private void cargarPoses() {
        poses.put("p1", "resources/truenoNormal.png");
        poses.put("p2", "resources/truenoPensante.png");
        poses.put("p3", "resources/truenoVacilon.png");

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
