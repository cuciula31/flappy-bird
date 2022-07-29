package utils;

import javafx.scene.shape.Rectangle;
import java.util.List;

public class Collision {

    //Aceasta metoda va returna adevarat sau fals daca pasarea atinge sau nu unul din tuburi
    public Boolean collisionDetection(List<Rectangle> pipes , Rectangle bird){
        for (Rectangle pipe: pipes) {
            if(Checkers.checkIfBirdTouchesPipe(bird, pipe)){
                return true;
            }
        }
        return  false;
    }

}
