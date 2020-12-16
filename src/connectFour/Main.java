package connectFour;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application{
    GameController game = new GameController();

    @Override
    public void start(Stage primaryStage) {
        game.setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
