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
public abstract class Items {
    
    private String nombre;
    private String descripcion;
    private Image imgItem;
    private ImageView imagenItem;

    public Items() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImgItem() {
        return imgItem;
    }

    public void setImgItem(Image imgItem) {
        this.imgItem = imgItem;
        this.imagenItem = new ImageView(imgItem);
    }

    public ImageView getImagenItem() {
        return imagenItem;
    }
    
    public String mostrarInformacionItem(){
        String informacion = "Nombre: " + nombre + "\n\n"
                +"Descripcion: " + descripcion;
        return informacion;
    }
}
    
