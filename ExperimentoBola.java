import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import java.util.*;
import javafx.scene.shape.Shape;
public class ExperimentoBola extends Application
{
    private int velocidadEnX;
    private int velocidadEnY;
    private int velocidadPlataforma1;
    private int velocidadPlataforma2;
    private static final int RADIO = 20;
    private int tiempoEnSegundos;
    private Random aleatorio = new Random();
    private int aleatorioX = aleatorio.nextInt();
    private int aleatorioY = aleatorio.nextInt();
    private int golesJ1;
    private int golesJ2;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        Group contenedor = new Group();

        velocidadEnX = 2;
        velocidadEnY = 2;
        tiempoEnSegundos = 70;
        golesJ1 = 0;
        golesJ2 = 0;

        Bola bola = new Bola();

        Plataforma plataforma1 = new Plataforma(0 , 500);
        plataforma1.setX(225);
        plataforma1.setY(480);
        contenedor.getChildren().add(plataforma1);

        Plataforma plataforma2 =  new Plataforma(0,500);
        plataforma2.setX(225);
        plataforma2.setY(40);
        contenedor.getChildren().add(plataforma2);
        contenedor.getChildren().add(bola);

        Label tiempoPasado = new Label("0");
        tiempoPasado.setTextFill(Color.WHITE);
        contenedor.getChildren().add(tiempoPasado);

        Label puntosJ1 = new Label("J1 : " + golesJ1);
        puntosJ1.setTextFill(Color.WHITE);
        puntosJ1.setLayoutY(480);
        contenedor.getChildren().add(puntosJ1);

        Label puntosJ2 = new Label("J2 : " + golesJ2);
        puntosJ2.setTextFill(Color.WHITE);
        puntosJ2.setLayoutX(460);
        contenedor.getChildren().add(puntosJ2);

        Scene escena = new Scene(contenedor, 500, 500 , Color.BLACK);
        escenario.setScene(escena);
        escenario.show();

        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.01), event -> {
                    // Controlamos si la bola rebota a izquierda o derecha
                    if (bola.getBoundsInParent().getMinX() <= 0 || bola.getBoundsInParent().getMaxX() >= escena.getWidth()) {
                        velocidadEnX = -velocidadEnX;                              
                    }

                    if (bola.comprobarChoquePlataforma(plataforma1)){
                        velocidadEnY = -velocidadEnY;
                    }

                    if (bola.comprobarChoquePlataforma(plataforma2)) {
                        velocidadEnY = -velocidadEnY;
                    }

                    bola.setTranslateX(bola.getTranslateX() + velocidadEnX);
                    bola.setTranslateY(bola.getTranslateY() + velocidadEnY);

                    plataforma1.setTranslateX(plataforma1.getTranslateX() + velocidadPlataforma1);
                    plataforma2.setTranslateX(plataforma2.getTranslateX() + velocidadPlataforma2);

                    if (plataforma1.getBoundsInParent().getMinX() == 0  || plataforma1.getBoundsInParent().getMaxX() == escena.getWidth()) {
                        velocidadPlataforma1 = 0;
                    }

                    if(plataforma2.getBoundsInParent().getMinX() == 0 || plataforma2.getBoundsInParent().getMaxX() == escena.getWidth()){
                        velocidadPlataforma2 = 0;
                    }

                    // Actualizamos la etiqueta del tiempo
                    int minutos = tiempoEnSegundos / 60;
                    int segundos = tiempoEnSegundos % 60;
                    tiempoPasado.setText(minutos + ":" + segundos);                        

                    if (bola.getBoundsInParent().getMinY() >= escena.getHeight()) {
                        golesJ2++;
                        puntosJ1.setText("J2 : " + golesJ2);
                        bola.recolocar();
                    }

                    if (bola.getBoundsInParent().getMinY() <= 0) {
                        golesJ1++;
                        puntosJ1.setText("J1 : " + golesJ1);
                        bola.recolocar();
                    }

                    if(golesJ1 == 5){
                        Label ganoJ1 = new Label("Ha ganado el J1");
                        ganoJ1.setTextFill(Color.WHITE);
                        ganoJ1.setLayoutX(escena.getWidth() / 2);
                        ganoJ1.setLayoutY(escena.getWidth() / 2);
                        contenedor.getChildren().add(ganoJ1);
                        timeline.stop();
                    }

                    if(golesJ2 == 5){
                        Label ganoJ2 = new Label("Ha ganado el J2");
                        ganoJ2.setTextFill(Color.WHITE);
                        ganoJ2.setLayoutX(escena.getWidth() / 2);
                        ganoJ2.setLayoutY(escena.getWidth() / 2);
                        contenedor.getChildren().add(ganoJ2);
                        timeline.stop();
                    }
                });  
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();     

        escena.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.RIGHT && 
                plataforma1.getBoundsInParent().getMaxX() != escena.getWidth()) {
                    velocidadPlataforma1 = 2;
                }
                else if (event.getCode() == KeyCode.LEFT && plataforma1.getBoundsInParent().getMinX() != 0) {
                    velocidadPlataforma1 = -2;
                }
                else if (event.getCode() == KeyCode.D && plataforma2.getBoundsInParent().getMaxX() != escena.getWidth()) {
                    velocidadPlataforma2 = 2;
                }
                else if (event.getCode() == KeyCode.A && plataforma2.getBoundsInParent().getMinX() != 0) {
                    velocidadPlataforma2 = -2;
                }
            });

        TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    tiempoEnSegundos++;
                }                        
            };
        Timer timer = new Timer();
        timer.schedule(tarea, 0, 1000);

    }
}
