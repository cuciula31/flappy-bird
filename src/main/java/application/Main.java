package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;

public class Main extends Application {


    /*
    Metoda start, este o metoda mostenita din clasa Appplication, clasa main a aplicatiei noastre, mostenind de altfel clasa.
    Aceasta metoda va fi responsabila pentru initializarea panoului vizibil de utilizator.
    Va fi pusa la dispozitie, fxml-ul din care sa preia template-ul, teoretic constructia view-ului.
    Dupa incarcarea fisierului FXML se vor stabili diverse detalii legate de modul de functionare,
    in cazul nostru se va seta titlul aplicatiei, dimensiunea scenei pe care sa fie afisat FXML-ul,
    tipul ferestrei, care este unul undecorated, adica fara margini si componente dimensionale pentur un aspect mai placut,
    dar si metoda de afisare show()
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        final String FXML_PATH = "sample.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_PATH));
        Parent root = loader.load();

        primaryStage.setTitle("Flappy-Bird");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}
