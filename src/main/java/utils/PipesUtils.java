package utils;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
//Aceasta clasa este utilizata ca si utilitar pentru clasa PipesGenerator, pentru a respecta principiile clean code.
public class PipesUtils {
    public static void setX(Rectangle pipe){pipe.setX(pipe.getX() + -0.75);}

    public static void removePipesIfOutOfBound(List<Rectangle> outOfScreenPipes,List<Rectangle> pipesFromLocalList, List<Node> pipesFromGameWindow){
        pipesFromLocalList.removeAll(outOfScreenPipes);
        pipesFromGameWindow.removeAll(outOfScreenPipes);
    }

    public static void addPipes(Rectangle topPipe, Rectangle bottomPipe, ObservableList<Node> gamePaneChildren){
        gamePaneChildren.addAll(topPipe,bottomPipe);
    }

    public static void setPipesToGreen(Rectangle topPipe, Rectangle bottomPipe){
        topPipe.setFill(Color.GREEN);
        bottomPipe.setFill(Color.GREEN);
    }

}
