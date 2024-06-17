/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import javafx.scene.layout.StackPane;
import vista.VistaInicio;
import lombok.*;

@Data
public class ControladorInicio {

    private VistaInicio vistaInicio;
    

    public ControladorInicio() {
        this.vistaInicio = new VistaInicio();
        
    }

 

}
