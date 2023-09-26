package sunat.gob.pe.ejercicio08;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
    
    public static Scene scene;

    @Override
    public void start(Stage stage) {
        
        ClockPane clockPane = new ClockPane();
        
        HBox hBox = new HBox(5);
        Button btnIniciar = new Button("Iniciar");
        Button btnParar = new Button("Parar");
        ToggleButton tbCambiar = new ToggleButton("Cambiar");
        
        hBox.getChildren().addAll(btnIniciar, btnParar, tbCambiar);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0, 10, 0));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(clockPane);
        borderPane.setBottom(hBox);
        
        clockPane.widthProperty().addListener(ov -> {
            clockPane.setW(borderPane.getWidth());
        });
        
        clockPane.heightProperty().addListener(ov -> {
            clockPane.setH(borderPane.getHeight());
        });
        
        Timeline lineaTiempo = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            clockPane.obtenerHoraActual();
        }), new KeyFrame(Duration.seconds(1)));
        lineaTiempo.setCycleCount(Animation.INDEFINITE);
        
        btnParar.setDisable(true);
        
        btnIniciar.setOnAction((ActionEvent t) -> {
            lineaTiempo.play();
            btnIniciar.setDisable(true);
            btnParar.setDisable(false);
        });
        
        btnParar.setOnAction((ActionEvent t) -> {
            lineaTiempo.stop();
            btnIniciar.setDisable(false);
            btnParar.setDisable(true);
        });
        
        tbCambiar.setOnAction((ActionEvent t) -> {
            try{
                cambiarDigital();    
            }catch(IOException ie){
                ie.printStackTrace();
            }
        });
        
        scene = new Scene(borderPane, 250, 300);
                stage.setTitle("Reloj Analógico");        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public void cambiarDigital() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("relojDigital.fxml"));
        Parent digital = loader.load();
        App.scene.setRoot(digital);
    }

}