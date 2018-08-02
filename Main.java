package connectFour;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
