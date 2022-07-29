package utils;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//Aceasta classa este responsabila cu generarea tuburilor.
public class PipesGenerator {

    private final Pane gamePane;
    private final double gameWindowWidth;
    private final double gameWindowHeight;
    private final Random random = new Random();

    public PipesGenerator(Pane plane, double gameWindowWidth, double gameWindowHeight) {
        this.gamePane = plane;
        this.gameWindowWidth = gameWindowWidth;
        this.gameWindowHeight = gameWindowHeight;
    }

    /*
    Aceasta metoda este cea care practic va crea tuburile. Vom pasa ca parametrii dimensiuni specifice, ghidate de Random,
    pentru a nu avea dimensiuni exacte pentru toate tuburile. Se va creea cate un tub superior, si inferior, la fiecare
    //apelare a acestei metode. De asemenea, aici este stabilita culoarea lor, nefiind specificata din vreun FXML, elementele fiind adaugate dinamic
     */
    public ArrayList<Rectangle> createPipes() {

        int pipeWidth = 75;
        double space = 200;
        double pipeTopHeight = random.nextInt((int) (gameWindowWidth - space - 100)) + 50;
        double pipeBottomHeight = gameWindowWidth - space - pipeTopHeight;

        Rectangle topPipe = new Rectangle(gameWindowHeight, 0, pipeWidth, pipeTopHeight);
        Rectangle bottomPipe = new Rectangle(gameWindowHeight, pipeTopHeight + space, pipeWidth, pipeBottomHeight);


        PipesUtils.setPipesToGreen(topPipe, bottomPipe);
        PipesUtils.addPipes(topPipe,bottomPipe,gamePane.getChildren());
        return new ArrayList<>(Arrays.asList(topPipe, bottomPipe));
    }

    //Aceasta metoda este responsabila cu eliminarea tuburilor inutile, care se afla in afara vederii noastre, dar si in afara
    //panoului de joc, la extrema stanga. Eliminarea lor ajuta la eficientizarea jocului. Daca nu s-ar intampla acest lucru
    //Am putea avea pana la zeci, sute sau chiar mii, in functie de jucator, ceea ce ar aduce la o incetinire progresiva a
    //jocului, dar si la o experienta de joc neplacuta

    public void movePipesOutOfGamePane(ArrayList<Rectangle> pipes) {

        ArrayList<Rectangle> outOfScreen = new ArrayList<>();

        pipes.forEach((pipe) -> {
            movePipe(pipe);
            if (Checkers.checkIfPipeXIsOutOfGamePane(pipe)) {
                outOfScreen.add(pipe);
            }
        });

        PipesUtils.removePipesIfOutOfBound(outOfScreen, pipes, gamePane.getChildren());
    }


    //Aceasta metoda este responsabila pentru mutarea tubului spre extrema stanga pe axa X
    private void movePipe(Rectangle pipe) {
        PipesUtils.setX(pipe);
    }

}
