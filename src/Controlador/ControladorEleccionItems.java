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
import modelo.Jugador;
import vista.VistaEleccionItems;

/**
 *
 * @author Luis
 */
public class ControladorEleccionItems {

    private GafasSol gafasSol;
    private Trineo trineo;

    //  Nodos vista
    private VistaEleccionItems vistaItems;
    private Spinner<Double> cantDinero;
    private Label descItem;
    private ImageView imgItem;
    private Map<String, Image> imagenItem;

    private HBox contenedorJuego;
    private CheckBox item1;
    private CheckBox item2;
    private Button btnAceptarItems;
    private Button btnContinuarJuego;
    
  
    public ControladorEleccionItems() {
        this.vistaItems = new VistaEleccionItems();
        inicializarComponentes();
        iniciarEventos();

    }

    private void inicializarComponentes() {

        contenedorJuego = vistaItems.getContenedorJuego();

        //  Items Jugador
        cantDinero = vistaItems.getCantidadDinero();

        imagenItem = vistaItems.getPathImgItem();
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
            mensaje = mensaje + ' ' + nGafas + ", " ;
            jugador.añadirItem(gafasSol);
        }

        if (item2.isSelected()) {
            mensaje = mensaje + ' ' + nTrineo;

            jugador.añadirItem(trineo);

        }

        showInformationAlert(mensaje + ". Esperemos que no te falte de nada.");

    }

    private void eventosBotones() {
        btnAceptarItems.setOnAction(event -> {
            if (!item1.isSelected() && !item2.isSelected()) {
                showInformationAlert("Elige al menos algo que llevarte. No vaya a ser...");
            } else {
                guardarItemsJugador();
            }
        });

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

    public VistaEleccionItems getVistaItems() {
        return vistaItems;
    }

    public HBox getContenedorJuego() {
        return contenedorJuego;
    }

    public Button getBtnAceptarItems() {
        return btnAceptarItems;
    }

    public Button getBtnContinuarJuego() {
        return btnContinuarJuego;
    }

    
    
    private void showInformationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
