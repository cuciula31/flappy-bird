package utils;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Bird {

    //Aceasta clasa este un handler pentru patratul cu rolul de pasare, aici se vor regasi actiunile pasarii.

    private final Rectangle bird;
    private final int jumpHeight;
    private final Collision collision = new Collision();

    public Bird(Rectangle bird, int jumpHeight) {
        this.bird = bird;
        this.jumpHeight = jumpHeight;
    }

    /*
    Aceasta metoda face responsabila actiunea de zbor a pasarii chemata de fiecare data cand panoul principal va detecta ca input
    click.
    Se va verifica daca pozitia pasarii nu este in afara dimensiunilor de joc. Daca dimensiunile se afla in parametrii optimi pentru
    continuarea jocului, se va schimba pozitia pasarii pe axa y, practic, va urca in inaltime.
    */
    public void fly(){
        double movement=-jumpHeight;
        if (Checkers.checkIfBirdHeightIsInGameHeightBound(bird, jumpHeight)){
            movement=BirdUtils.setMovement(bird);
        }
        moveBirdY(movement);
    }

    //Metoda responsabila pentru modificarea pozitiei pe axa Y

    public void moveBirdY(double positionChange){
        BirdUtils.setYForBird(bird, positionChange);
    }

    //Aceasta metoda este apelata in metoda update() din Controller, aceasta va returna continuu daca pasarea a intrat sau nu in coliziune cu unul din tuburi.
    public Boolean isBirdDead(ArrayList<Rectangle> pipes, Pane gamePane){
        if(collision.collisionDetection(pipes, bird)){
            return true;
        }
        return Checkers.checkIfBirdYIsInGameHeightBound(bird, gamePane);
    }
}
