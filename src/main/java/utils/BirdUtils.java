package utils;

import javafx.scene.shape.Rectangle;
//Aceasta clasa este utilizata ca si utilitar pentru clasa Bird, pentru a respecta principiile clean code.
public class BirdUtils {

    public static void setYForBird(Rectangle bird, double positionChange){
        bird.setY(bird.getY() + positionChange);
    }

    public static double setMovement(Rectangle bird){
        return -(bird.getLayoutY() + bird.getY());
    }

    public static double setBirdY(Rectangle bird){
        return bird.getLayoutY() + bird.getY();
    }

}
