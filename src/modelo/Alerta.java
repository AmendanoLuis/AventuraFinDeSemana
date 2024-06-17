package modelo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.*;

@Data
public class Alerta {

    private StackPane cAlerta;
    private VBox vAlerta;
    private Label tituloInfo;
    private Separator separador;
    private Label info;
    private Button btnAlerta;

    private ImageView imagen;

    public Alerta() {
        tituloInfo = new Label("");
        separador = new Separator();
        info = new Label("");
        btnAlerta = new Button("Aceptar");

        tituloInfo.setMinSize(100, 50);
        tituloInfo.setPadding(new Insets(10));
        
        info.setMinSize(250, 200);
        info.setPadding(new Insets(10));

        cAlerta = new StackPane();
        vAlerta = new VBox(20);

        vAlerta.setAlignment(Pos.CENTER);

        tituloInfo.setWrapText(true);
        info.setWrapText(true);

        cAlerta.setMinSize(250, 200);
        cAlerta.setMaxSize(450, 320);

        vAlerta.getChildren().addAll(tituloInfo, separador, info, btnAlerta);

        cAlerta.getChildren().add(vAlerta);

        cAlerta.setVisible(false);

        btnAlerta.setOnAction(event -> retirarAlerta());

        setIdTexto();
    }

    public Alerta(Image img, int alto, int ancho) {
        tituloInfo = new Label("");
        separador = new Separator();

        imagen = new ImageView(img);
        btnAlerta = new Button("Aceptar");

        tituloInfo.setMinSize(100, 50);
        imagen.setFitHeight(alto);
        imagen.setFitWidth(ancho);

        cAlerta = new StackPane();
        vAlerta = new VBox(20);
        vAlerta.setSpacing(10);

        tituloInfo.setWrapText(true);

        cAlerta.setMinSize(300, 200);
        cAlerta.setMaxSize(350, 320);

        vAlerta.getChildren().addAll(tituloInfo, separador, imagen, btnAlerta);

        cAlerta.getChildren().add(vAlerta);

        vAlerta.setAlignment(Pos.CENTER);

        cAlerta.setVisible(false);

        btnAlerta.setOnAction(event -> retirarAlerta());

        setIdImage();

    }

    private void setIdTexto() {
        tituloInfo.setId("tituloAlerta");
        separador.setId("separador");
        info.setId("infoAlerta");
        btnAlerta.setId("botonAlerta");
        vAlerta.setId("vAlerta");
        cAlerta.setId("contenedorAlerta");

    }

    private void setIdImage() {
        tituloInfo.setId("tituloAlerta");
        separador.setId("separador");
        imagen.setId("imagenAlerta");
        btnAlerta.setId("botonAlerta");
        vAlerta.setId("vAlerta");
        cAlerta.setId("contenedorAlerta");
    }

    public void mostrarAlertaConImagen(String titulo) {
        tituloInfo.setText(titulo);

        cAlerta.setVisible(true);
        cAlerta.toFront();
    }

    public void mostrarAlerta(String titulo, String mensaje) {
        tituloInfo.setText(titulo);
        info.setText(mensaje);

        cAlerta.setVisible(true);
        cAlerta.toFront();
    }

    public void retirarAlerta() {
        cAlerta.setVisible(false);
    }

    public StackPane getAlerta() {
        return cAlerta;
    }
}
