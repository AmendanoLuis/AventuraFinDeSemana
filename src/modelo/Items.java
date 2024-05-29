/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class Items {
    
    private String nombre;
    private String descripcion;
    private Image imgItem;
    private ImageView imagenItem;

    public Items() {
      
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ImageView getImagenItem() {
        return imagenItem;
    }

    public void setImgItem(Image imgItem) {
        this.imgItem = imgItem;
    }
    
    public String mostrarInformacionItem(){
        String informacion = "Nombre: " + nombre + "\n" + "\n"
                +"Descripcion: " + descripcion;
        return informacion;
    }
    
    
    
    
    
}
