package utils;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
//Aceasta clasa este un utilitar care va returna diverse boolean-uri. Scopul acesteia este de a face mai citibile
//conditionalele, respectand astfel principiile clean code
public class Checkers {
    public static Boolean checkIfPipeXIsOutOfGamePane(Rectangle rectangle){
        return rectangle.getX() <= -rectangle.getWidth();
    }

    public static Boolean checkIfBirdHeightIsInGameHeightBound(Rectangle bird, double jumpHeight){
        return bird.getLayoutY() + bird.getY() <= jumpHeight;
    }

    public static Boolean checkIfBirdYIsInGameHeightBound(Rectangle bird, Pane gamePane){
        return BirdUtils.setBirdY(bird) >= gamePane.getHeight();
    }

    public static Boolean checkIfBirdTouchesPipe(Rectangle bird, Rectangle pipe){
        return pipe.getBoundsInParent().intersects(bird.getBoundsInParent());
    }

    public static Boolean pointChecker(ArrayList<Rectangle> obstacles, Rectangle bird){
        for (Rectangle obstacle: obstacles) {
            int birdPositionX = (int) (bird.getLayoutX() + bird.getX());
            if(((int)(obstacle.getLayoutX() + obstacle.getX()) == birdPositionX)){
                return true;
            }
        }
        return false;
    }

    public static Boolean checkGameTime(int gameTime){
        return gameTime % 500 == 0;
    }

}
