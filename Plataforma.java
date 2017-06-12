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
/**
 * Write a description of class Plataforma here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plataforma extends Rectangle
{
    private static final int TAMANIO_PLATAFORMAX = 50;
    private static final int TAMANIO_PLATAFORMAY = 5;
    private int velocidadPlataforma;
    private int posicionMin;
    private int posicionMax;
    public Plataforma(int posicionMinima , int posicionMaxima){
        setWidth(TAMANIO_PLATAFORMAX);
        setHeight(TAMANIO_PLATAFORMAY);
        setFill(Color.WHITE);
        velocidadPlataforma = 1;
        posicionMin = posicionMinima;
        posicionMax = posicionMaxima;
    }

    public void cambiarDireccionDerecha(){
        if(getBoundsInParent().getMaxX() != posicionMax){
            velocidadPlataforma = 1;
        }
    }

    public void cambiarDireccionIzquierda(){
        if(getBoundsInParent().getMinX() != posicionMin){
            velocidadPlataforma = -1;
        }
    }

    public void desplazar(){
        this.setTranslateX(this.getTranslateX() + velocidadPlataforma);
        if(getBoundsInParent().getMinX() == posicionMin || getBoundsInParent().getMaxX() == posicionMax){
            velocidadPlataforma = 0;
        }
    }
}