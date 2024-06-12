/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package items;

import javafx.scene.image.Image;
import modelo.Items;

/**
 *
 * @author Luis
 */
public class Trineo extends Items {

    private String pathImage = "resources/trineo.png";

    public Trineo() {
        setNombre("Trineo");
        setDescripcion("Un trineo que puedes usar en algún lugar con nieve.");
        setImgItem(new Image(pathImage));
    }

    public String getPathImage() {
        return pathImage;
    }



}
