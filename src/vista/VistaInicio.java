package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class VistaInicio {

    private VBox pInicio;
    private Button btnInicio;
    private ImageView imagenInicio;
    private Text autor;

    public VistaInicio() {

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        pInicio = new VBox(20);
        pInicio.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        
        cargarLogotipo();

        cargarBotonIniciarJuego();

        pInicio.getChildren().add(imagenInicio);

        pInicio.getChildren().add(btnInicio);

        cargarAutorJuego();

        pInicio.getChildren().add(autor);

        pInicio.setAlignment(Pos.CENTER);
    }

    private void cargarAutorJuego() {
        autor = new Text("Created by Luis Augusto");
        autor.setFill(Color.GREEN);
        autor.setTextAlignment(TextAlignment.LEFT);
        
        VBox.setMargin(autor, new Insets(0, 0, 20, 0));
    }

    private void cargarLogotipo() {
        String path = "resources/LOGOTIPO.png";
        Image icono = new Image(path);
        imagenInicio = new ImageView(icono);
    }

    private void cargarBotonIniciarJuego() {
        btnInicio = new Button("Inicio");
        btnInicio.setStyle(
                "-fx-background-color: #c2fbd7; "
                + "-fx-border-radius: 100px; "
                + "-fx-box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset, rgba(44, 187, 99, .15) 0 1px 2px, "
                + "rgba(44, 187, 99, .15) 0 2px 4px, rgba(44, 187, 99, .15) 0 4px 8px, rgba(44, 187, 99, .15) 0 8px"
                + " 16px, rgba(44, 187, 99, .15) 0 16px 32px; "
                + "-fx-text-fill: green; "
                + "-fx-cursor: hand; "
                + "-fx-font-family: 'CerebriSans-Regular', 'Arial'; "
                + "-fx-font-size: 16px; "
                + "-fx-padding: 15px 25px; "
                + "-fx-text-alignment: center; "
                + "-fx-border-width: 0; "
                + "-fx-transition: all 250ms; "
                + "-fx-user-select: none; "
                + "-webkit-user-select: none; "
                + "-fx-touch-action: manipulation;"
        );
    }

    public VBox getContenedorJuego() {
        return pInicio;
    }

    public ImageView getImagenInicio() {
        return imagenInicio;
    }

    public Text getAutor() {
        return autor;
    }

    public Button getBtnInicio() {
        return btnInicio;
    }

}
