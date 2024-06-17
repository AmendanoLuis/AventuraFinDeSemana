/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import items.GafasSol;
import items.Trineo;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import modelo.Alerta;
import modelo.Jugador;
import vista.VistaEleccionItems;
import lombok.*;

/**
 *
 * @author Luis
 */
@Data
public class ControladorEleccionItems {

    private GafasSol gafasSol;
    private Trineo trineo;

    //  Nodos vista
    private VistaEleccionItems vistaItems;
    private Spinner<Double> cantDinero;
    private Label descItem;
    private ImageView imgItem;
    private Map<String, Image> imagenItem;

    private StackPane contenedorJuego;
    private CheckBox item1;
    private CheckBox item2;
    private Button btnAceptarItems;
    private Button btnContinuarJuego;

    //  Alerta
    private Alerta alerta;
    private String instruccionesEleccionItems = "Elige los items y la cantidad de dinero que creas necesarios."
            + "\nLee atentamente para que sirven los objetos, te pueden ser muy utiles."
            + "\nY no te olvides del dinero";
    private String reglasMinijuego
            = "1- Ten cuidado es un viaje muy largo, alguien puede necesitar ir al baño"
            + "\n" + "2- Para el coche cuando alguien necesite  ir al baño. "
            + "\n" + "3- Llega al destino sin que nadie se mee encima.";

    public ControladorEleccionItems() {
        this.vistaItems = new VistaEleccionItems();
        inicializarComponentes();
        iniciarEventos();

        mostrarInstrucciones();
    }

    private void mostrarInstrucciones() {
        alerta.mostrarAlerta("Eleccion Items", instruccionesEleccionItems);
    }

    private void inicializarComponentes() {

        alerta = new Alerta();

        contenedorJuego = vistaItems.getContenedorJuego();
        contenedorJuego.getChildren().add(alerta.getAlerta());

        //  Items Jugador
        cantDinero = vistaItems.getCantidadDinero();

        imagenItem = vistaItems.getImagenItem();
        item1 = vistaItems.getItem1();
        item2 = vistaItems.getItem2();
        gafasSol = new GafasSol();
        trineo = new Trineo();

        //  Imagen y desc de Items
        descItem = vistaItems.getDescItem();
        imgItem = vistaItems.getImgItem();

        //  Botones
        btnAceptarItems = vistaItems.getBtnAceptarItems();
        btnContinuarJuego = vistaItems.getBtnContinuarJuego();

    }

    private void iniciarEventos() {
        eventosBotones();
        cambiarImagenYDescripcionItem();

    }

    private void guardarItemsJugador() {
        Jugador jugador = Jugador.getInstanciaJugador();

        String mensaje = "Items: ";

        String nGafas = gafasSol.getNombre();
        String nTrineo = trineo.getNombre();

        //  Dinero
        double dineroJ = cantDinero.getValue();
        jugador.agregarDinero(dineroJ);

        if (item1.isSelected()) {
            mensaje = mensaje + ' ' + nGafas + ", ";
            jugador.añadirItem(gafasSol);
        }

        if (item2.isSelected()) {
            mensaje = mensaje + ' ' + nTrineo;

            jugador.añadirItem(trineo);

        }

        alerta.mostrarAlerta("Eleccion Items", mensaje + ". Esperemos que no te falte de nada.");

    }

    private void eventosBotones() {
        btnAceptarItems.setOnAction(event -> {
            if (!item1.isSelected() && !item2.isSelected()) {
                alerta.mostrarAlerta("Eleccion Items", "Elige al menos algo que llevarte. No vaya a ser...");

            } else {
                guardarItemsJugador();
            }
        });

    }

    public void mostrarReglasMinijuego() {
     alerta.mostrarAlerta("REGLAS MINIJUEGO", reglasMinijuego);

    }

    private void cambiarImagenYDescripcionItem() {

        item1.selectedProperty().addListener((observable, oldValue, newValue) -> {

            String descGafas = gafasSol.getDescripcion();

            if (newValue) {
                imgItem.setImage(imagenItem.get("Gafas Sol"));
                descItem.setText(descGafas);
                System.out.println(descGafas);
            } else {
                imgItem.setImage(null);
                descItem.setText(null);
            }
        });

        item2.selectedProperty().addListener((observable, oldValue, newValue) -> {

            String descTrineo = trineo.getDescripcion();

            if (newValue) {
                imgItem.setImage(imagenItem.get("Trineo"));
                descItem.setText(descTrineo);
                System.out.println(descTrineo);

            } else {
                imgItem.setImage(null);
                descItem.setText(null);
            }
        });
    }

}
