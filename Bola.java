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
 * Write a description of class Bola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bola extends Circle
{
    private static final int RADIO = 20;
    private int velocidadEnX;
    private int velocidadEnY;
    public Bola(){
        setFill(Color.WHITE);  
        setRadius(RADIO);
        velocidadEnX = 3;
        velocidadEnY = 3;
        Random aleatorio = new Random();
        setCenterX(50 + aleatorio.nextInt(500 - 40));
        setCenterY(100);
    }

    public boolean comprobarChoquePlataforma(Plataforma plataforma){
        boolean choque = false;
        Shape interseccion = Shape.intersect(this,plataforma);
        double valorInterseccion = interseccion.getBoundsInParent().getWidth();
        if(valorInterseccion != -1){
            choque = true;
        }
        return choque;
    }

    public void desplazar(){
        setTranslateX(getTranslateX() + velocidadEnX);
        setTranslateY(getTranslateY() + velocidadEnY);
    }
    
    public void recolocar(){
        Random aleatorio = new Random();
        setCenterX(50 + aleatorio.nextInt(500 - 40));
        setCenterY(100);
    }
}