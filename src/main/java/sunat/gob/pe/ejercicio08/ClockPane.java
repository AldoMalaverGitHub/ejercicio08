/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sunat.gob.pe.ejercicio08;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Aldo Malaver
 */
public class ClockPane extends Pane {

    private int hora;

    private int minuto;

    private int segundo;

    private double width = 250;

    private double height = 250;

    public ClockPane() {
        obtenerHoraActual();
    }

    public ClockPane(int hora, int minuto, int segundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public double getW() {
        return width;
    }

    public void setW(double width) {
        this.width = width;
    }

    public double getH() {
        return height;
    }

    public void setH(double height) {
        this.height = height;
    }

    public void obtenerHoraActual() {

        Calendar calendar = new GregorianCalendar();

        this.hora = calendar.get(Calendar.HOUR_OF_DAY);
        this.minuto = calendar.get(Calendar.MINUTE);
        this.segundo = calendar.get(Calendar.SECOND);
        pintarReloj();

        /* LocalDateTime fechaActual = LocalDateTime.now();
        this.hora = fechaActual.getHour();
        this.minuto = fechaActual.getMinute();
        this.segundo = fechaActual.getSecond();*/
    }
    
    public void obtenerHoraActualDigital(){
        LocalDateTime fechaActual = LocalDateTime.now();
        this.hora = fechaActual.getHour();
        this.minuto = fechaActual.getMinute();
        this.segundo = fechaActual.getSecond();
    }

    private void pintarReloj() {

        //Inicializar parametros de reloj
        double radioReloj = Math.min(getW(), getH()) * 0.8 * 0.5;
        double centerX = getW() / 2;
        double centerY = getH() / 2;

        //Dibujo el circulo
        Circle circulo = new Circle(centerX, centerY, radioReloj);
        circulo.setFill(Color.WHITE);
        circulo.setStroke(Color.BLACK);

        Text doceTexto = new Text(centerX - 5, centerY - radioReloj + 12, "12");

        Text nueveTexto = new Text(centerX - radioReloj + 3, centerY + 5, "9");

        Text tresTexto = new Text(centerX + radioReloj - 10, centerY + 3, "3");

        Text seisTexto = new Text(centerX - 3, centerY + radioReloj - 3, "6");

        //Dibujar la linea de segundos
        double sLength = radioReloj * 0.8;
        double segundoX = centerX + sLength * Math.sin(segundo * (2 * Math.PI / 60));
        double segundoY = centerY - sLength * Math.cos(segundo * (2 * Math.PI / 60));

        Line lineaSegundo = new Line(centerX, centerY, segundoX, segundoY);
        lineaSegundo.setStroke(Color.RED);

        //dibujar la linea de minutos
        double mLength = radioReloj * 0.65;
        double minutoX = centerX + mLength * Math.sin(minuto * (2 * Math.PI / 60));
        double minutoY = centerY - mLength * Math.cos(minuto * (2 * Math.PI / 60));

        Line lineaMinuto = new Line(centerX, centerY, minutoX, minutoY);
        lineaMinuto.setStroke(Color.BLUE);

        //dibujar la linea de horas
        double hLength = radioReloj * 0.5;
        double horaX = centerX + hLength * Math.sin((hora % 12 + minuto / 60.0) * (2 * Math.PI / 12));
        double horaY = centerY - hLength * Math.cos((hora % 12 + minuto / 60.0) * (2 * Math.PI / 12));
        Line lineaHora = new Line(centerX, centerY, horaX, horaY);
        lineaHora.setStroke(Color.GREEN);

        //Agregando elementos al panel
        getChildren().clear();
        getChildren().addAll(circulo, doceTexto, nueveTexto, tresTexto, seisTexto,
                lineaSegundo, lineaMinuto, lineaHora);

    }

}
