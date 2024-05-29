/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

/**
 *
 * @author Luis
 */
public interface Personaje {
    
    String obtenerNombre();
    String obtenerDialogo(String clave);
    String obtenerPose(String clave);
    void generarDinero();
    double obtenerDinero();
    
    
}
