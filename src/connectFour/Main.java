package connectFour;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    GameController game = new GameController();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = game.getScene();
        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(scene);
        primaryStage.show();

        game.gameLoop();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
