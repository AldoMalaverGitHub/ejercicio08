/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.ejercicio08.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;
import sunat.gob.pe.ejercicio08.ClockPane;

/**
 * FXML Controller class
 *
 * @author Aldo Malaver
 */
public class DigitalController implements Initializable {
    
    @FXML
    private Label lblReloj;
    
    @FXML
    private Button btnIniciar;
    
    @FXML
    private Button btnParar;
    
    @FXML
    private ToggleButton tbCambiar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClockPane clockPane = new ClockPane();
        lblReloj.setText(devolverHoraDigital(clockPane.getHora(), 
                clockPane.getMinuto(), clockPane.getSegundo()));
        
        Timeline lineaTiempo = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            clockPane.obtenerHoraActualDigital();
            lblReloj.setText(devolverHoraDigital(clockPane.getHora(), 
                clockPane.getMinuto(), clockPane.getSegundo()));
            
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
        
    }

    private String devolverHoraDigital(int hora, int minuto, int segundo){
        StringBuilder horaDigital = new StringBuilder();
        horaDigital.append(hora).append(":").
                append(minuto).append(":").append(segundo);
        
        return horaDigital.toString();
    }
    
}
