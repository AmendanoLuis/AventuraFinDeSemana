/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Luis
 */
public class Jugador {

    private static Jugador instanciaJugador;

    private String nombre;
    private double dinero = 7.50;
    private Image imgDineroJugador;
    private ImageView iconoDineroJugador;
    private List<Items> items;

    private Jugador() {
        imgDineroJugador = new Image("images/jugador/iconoDinero.png");
        iconoDineroJugador = new ImageView(imgDineroJugador);
        items = new ArrayList();
        //  Altura
        iconoDineroJugador.setFitHeight(40);
        //  Ancho
        iconoDineroJugador.setFitWidth(40);

    }

    public static Jugador getInstanciaJugador() {

        if (instanciaJugador == null) {
            instanciaJugador = new Jugador();
        }

        return instanciaJugador;

    }
    
    public void añadirItem(Items i){
        items.add(i);
    }
    

    public String getNombre() {
        return nombre;
    }

    public double getDinero() {
        return dinero;
    }

     public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    

    public ImageView getIconoDineroJugador() {
        return this.iconoDineroJugador;
    }

    public List<Items> getItems() {
        return items;
    }

    

}
