/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import items.GafasSol;
import items.Trineo;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Luis
 */
public class VistaEleccionItems {
    //  Jugador

    private GafasSol gafasSol;
    private Trineo trineo;

    //  Nodos
    private Image item;
    private ImageView imgItem;
    private Label descItem;
    private Map<String, Image> imagenItem;
    private Spinner<Double> cantidadDinero;
    private Image dinero;
    private ImageView imgDinero;
    private Label textItems;
    private CheckBox item1;
    private CheckBox item2;
    private Button btnAceptarItems;
    private Button btnContinuarJuego;

    //  Contenedores
    private VBox contInfoItems;
    private VBox contDinero;
    private StackPane stackImageItem;
    private VBox contItems;
    private HBox contObjetos;
    private HBox contenedorJuego;
    private VBox contCheckItems;
    private VBox contBotones;

    public VistaEleccionItems() {
        inicializarComponentes();

    }

    private void inicializarComponentes() {

        // Inicialización de componentes
        inicializarContInfoItems();
        inicializarContItems();
        inicializarContDinero();
        inicializarContObjetos();
        inicializarContJuego();
        asignarIdsCss();
    }

    private void inicializarContInfoItems() {

        gafasSol = new GafasSol();
        trineo = new Trineo();
        imagenItem = new HashMap<>(); 
        cargarImagenesItems();
        
        item = new Image(gafasSol.getPathImage());
        imgItem = new ImageView(item);
        imgItem.setFitWidth(180);
        imgItem.setFitHeight(160);
        imgItem.setPreserveRatio(true);

        // Contenedor StackPane, contiene la imagen
        stackImageItem = new StackPane(imgItem);
        stackImageItem.setMaxWidth(Region.USE_PREF_SIZE);

        // Crear un borde con un color y un ancho específicos
        double anchoBordeItem = 4.0;
        BorderStroke bsItem = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(anchoBordeItem));
        Border bordeItem = new Border(bsItem);

        stackImageItem.setBorder(bordeItem);

        descItem = new Label();
        descItem.setId("descItem");

        contInfoItems = new VBox(20);  // Espaciado de 10
        contInfoItems.setAlignment(Pos.CENTER);
        contInfoItems.setMinSize(255, 300);

        contInfoItems.getChildren().addAll(stackImageItem, descItem);
        contInfoItems.setPadding(new Insets(10));

        VBox.setVgrow(contInfoItems, Priority.ALWAYS);
    }

    private void inicializarContItems() {

        item1 = new CheckBox("Gafas de Sol");
        item2 = new CheckBox("Trineo");

        item1.setAlignment(Pos.CENTER_LEFT);
        item2.setAlignment(Pos.CENTER_LEFT);

        // Aplicar estilos CSS para alinear el texto a la derecha
        item1.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        item2.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        // Contenedores
        textItems = new Label("ITEMS");

        contItems = new VBox(50);
        contItems.setAlignment(Pos.CENTER);

        contCheckItems = new VBox(10);
        contCheckItems.getChildren().addAll(item1, item2);
        contItems.getChildren().addAll(textItems, contCheckItems);
        contItems.setPadding(new Insets(10, 10, 10, 20));
        contItems.setMinSize(290, 550);

        VBox.setVgrow(contItems, Priority.ALWAYS);

        // Crear botones
        btnAceptarItems = new Button("Aceptar Eleccion");
        btnContinuarJuego = new Button("Continuar Juego");

// Crear VBox para los botones
        contBotones = new VBox(10); // 10 es el espacio entre los botones
        contBotones.setPadding(new Insets(10)); // Establecer un margen alrededor del VBox
        contBotones.setAlignment(Pos.BOTTOM_CENTER);

// Agregar los botones al VBox
        contBotones.getChildren().addAll(btnAceptarItems, btnContinuarJuego);

// Ajustar posición del VBox para que esté en la parte inferior con un margen
// Agregar el VBox al contenedor principal
        contItems.getChildren().add(contBotones);

    }

    private void inicializarContDinero() {

        cantidadDinero = new Spinner<>();

        dinero = new Image(getClass().getResource("/images/panelEleccionItems/dinero.png").toExternalForm());

        imgDinero = new ImageView(dinero);
        imgDinero.setFitHeight(150);
        imgDinero.setFitWidth(170);

        StackPane imagenDinero = new StackPane(imgDinero);
        imagenDinero.setMaxWidth(Region.USE_PREF_SIZE);

        double anchoBordeDinero = 4.0;
        BorderStroke bsDinero = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(anchoBordeDinero));
        Border bordeDinero = new Border(bsDinero);

        imagenDinero.setBorder(bordeDinero);

        SpinnerValueFactory<Double> valoresDinero = new SpinnerValueFactory.DoubleSpinnerValueFactory(40, 150, 40);
        cantidadDinero.setValueFactory(valoresDinero);

        contDinero = new VBox(20);  // Espaciado de 10
        contDinero.setPadding(new Insets(10, 10, 10, 10));
        contDinero.setAlignment(Pos.CENTER);

        contDinero.getChildren().addAll(imagenDinero, cantidadDinero);
        contDinero.setMinSize(290, 400);
        VBox.setVgrow(contDinero, Priority.ALWAYS);
    }

    private void inicializarContObjetos() {
        contObjetos = new HBox();  // Espaciado de 20
        contObjetos.setAlignment(Pos.CENTER);
        contObjetos.getChildren().addAll(contDinero, contItems);
        HBox.setHgrow(contObjetos, Priority.SOMETIMES);
    }

    private void inicializarContJuego() {

        contenedorJuego = new HBox();  // Espaciado de 30
        contenedorJuego.setAlignment(Pos.BOTTOM_CENTER);
        contenedorJuego.getChildren().addAll(contObjetos, contInfoItems);
    }

    private void asignarIdsCss() {
        textItems.setId("textItems");
        descItem.setId("descItem");
        contInfoItems.setId("contInfoItems");
        contDinero.setId("contDinero");
        contItems.setId("contItems");
        contenedorJuego.setId("contenedorJuego");
        cantidadDinero.setId("spinnerCantidadDinero");
        item1.setId("checkboxItem1");
        item2.setId("checkboxItem2");
        cantidadDinero.setId("cantidadDinero");
        item1.setId("item1");
        item2.setId("item2");
        contenedorJuego.setId("contenedorJuego");
    }

    private void cargarImagenesItems() {

        imagenItem.put("Gafas Sol", gafasSol.getImgItem());
        imagenItem.put("Trineo", trineo.getImgItem());

    }
    
   

    //  Getter
    public Spinner<Double> getCantidadDinero() {
        return cantidadDinero;
    }

    public Label getDescItem() {
        return descItem;
    }

    public Map<String, Image> getPathImgItem() {
        return imagenItem;
    }

    public HBox getContenedorJuego() {
        return contenedorJuego;
    }

    public CheckBox getItem1() {
        return item1;
    }

    public CheckBox getItem2() {
        return item2;
    }

    public Button getBtnAceptarItems() {
        return btnAceptarItems;
    }

    public Button getBtnContinuarJuego() {
        return btnContinuarJuego;
    }

    public ImageView getImgItem() {
        return imgItem;
    }

    //  Setters
    public void setImgItem(ImageView imgItem) {
        this.imgItem = imgItem;
    }

    public void setDescItem(Label descItem) {
        this.descItem = descItem;
    }

    public void setImgDinero(ImageView imgDinero) {
        this.imgDinero = imgDinero;
    }

    public void setTextItems(Label textItems) {
        this.textItems = textItems;
    }

}
