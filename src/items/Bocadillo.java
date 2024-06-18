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
public class Bocadillo extends Items {

    private String pathImage = "resources/bocadillo.png";

    public Bocadillo() {
        setNombre("Bocadillo");
        setDescripcion("Mira que bocadillo más rico, puede que "
                + "lo necesitemos despues.");
        setImgItem(new Image(pathImage));
    }

    public String getPathImage() {
        return pathImage;
    }
}
