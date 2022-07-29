package application;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utils.Bird;
import utils.Checkers;
import utils.PipesGenerator;

import java.util.ArrayList;

public class Controller {

    private AnimationTimer gameLoop;
    @FXML
    private Pane gamePane;
    @FXML
    private Rectangle bird;
    @FXML
    private Text scoreText;

    private double accelerationTime = 5;
    private int gameTime = 0;
    private int score = 0;
    private ArrayList<Rectangle> pipes = new ArrayList<>();
    private Bird birdHandler;
    private PipesGenerator pipesGenerator;

    //Metoda de initializare, ruleaza doar in momentul in care aplicatia este pornita, poate fi folosita pentru diverse initializari
    //de UI sau de variabile, in cazul nostru stabileste detalii privitoare la modul de joc. Se creaza o noua pasare, un generator de tuburi,
    // game loop-ul va primi o metoda de update, se va incarca jocul si va incepe game loop-ul.

    @FXML
    private void initialize() {
        final int jumpHeight = 15;
        birdHandler = new Bird(bird, jumpHeight);
        pipesGenerator = new PipesGenerator(gamePane, 600, 400);
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        Platform.runLater(this::load);
        gameLoop.start();
    }

    //Metoda birdAction() este utilizata atunci cand panoul principal primeste ca input de la mouse un click.
    //In momentul in care panoul principal va primi ca input click-ul, va fi apelata metoda fly din birdHandler care
    //va face pasarea sa zboare (sa isi modifice valorile pentru inaltime), dar si o reinitializare a timpului de acceleratie

    @FXML
    private void birdAction() {
        birdHandler.fly();
        accelerationTime = 5;
    }

    //Metoda update apelata in game loop va actualiza valori precum timpul de joc, timp de acceleratie, va
    //restabili valoarea yDelta la initial, dar va verifica si daca am obtinut un punct, va verfica daca exista panouri
    //pe care nu le mai vedem pentru a le putea elimina, rezultand o eficienta sporita, dar si daca pasarea, atinge un tub.
    //Pentru cazul in care pasarea atinge tubul, jocul se va reinitializa prin metoda resetGame.

    private void update() {
        gameTime++;
        accelerationTime++;
        double yDelta = 0.02;
        birdHandler.moveBirdY(yDelta * accelerationTime);

        //Verificare scor
        if(Checkers.pointChecker(pipes, bird)){
                score++;
                scoreText.setText(String.valueOf(score));
        }

        //Verificare daca exista tuburi in afara panoului de joc
        pipesGenerator.movePipesOutOfGamePane(pipes);
        if(Checkers.checkGameTime(gameTime)){
                pipes.addAll(pipesGenerator.createPipes());
        }

        //Verificare daca pasarea a atins tubul.
        if(birdHandler.isBirdDead(pipes, gamePane)){
           resetGame();
        }
    }

    //Metoda load este responsabila pentru initializarea tuburilor in panoul de joc
    private void load(){
        pipes.addAll(pipesGenerator.createPipes());
    }

    //Aceasta metoda, cum am zis si la metoda update(), va reinitializa jocul pentru cazul in care pasarea a atins tubul,
    //practic, jocul fiind luat de la inceput.
    private void resetGame(){
        bird.setY(0);
        gamePane.getChildren().removeAll(pipes);
        pipes.clear();
        gameTime = 0;
        accelerationTime = 5;
        score = 0;
        scoreText.setText("");
    }

}
