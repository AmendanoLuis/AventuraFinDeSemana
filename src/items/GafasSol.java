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
public class GafasSol extends Items {

      private String pathImage = "resources/gafasSol.png";

    public GafasSol() {
        setNombre("Gafas de Sol");
        setDescripcion("Unas gafas de sol muy chulas.");
        setImgItem(new Image(pathImage));
    }

    public String getPathImage() {
        return pathImage;
    }


}
