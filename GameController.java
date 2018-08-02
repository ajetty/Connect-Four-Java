package connectFour;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

public class GameController {
	
	GUI gui = new GUI();
	PlayerOne playerOne = new PlayerOne();
	PlayerTwo playerTwo = new PlayerTwo();
	
	public GameController() {};
	
	public void gameLoop() {
		boolean gameOver = false;
		
		
			//long = G1G2G3G4G5G60 F1F2F3F4F5F60 E1E2E3E4E5E60 D1D2D3D4D5D60 C1C2C3C4C5C60 B1B2B3B4B5B60 A1A2A3A4A5A60 0000000 0000000 0
			
		gui.getPlayerMove().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(playerOne.movePlayer(gui.getPlayerMove().get(), playerOne.getBitBoard(), playerTwo.getBitBoard()))
					System.out.println(true);
				else
					System.out.println(false);
			}
		});
			
			
	
	}
	
	public Scene getScene() {
		return gui.getScene();
	}
}
