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

    private String nombre;
    private String descripcion;

    public GafasSol() {
        super();

        establecerNombre();
        establecerDescripcion();
    }

    private void establecerNombre() {
        nombre = "Gafas de Sol";

    }

    private void establecerDescripcion() {
        descripcion = "Unas gafas de sol muy chulas.";

    }

    @Override
    public String mostrarInformacionItem() {
        return super.mostrarInformacionItem();
    }

    @Override
    public void setImgItem(Image imgItem) {
        super.setImgItem(imgItem);
    }

}
