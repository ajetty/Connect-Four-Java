package connectFour;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

public class GameController {
	
	GUI gui;
	PlayerOne playerOne;
	PlayerTwo playerTwo;
	boolean gameOver;

	
	public GameController() {
		gui = new GUI();
		playerOne = new PlayerOne();
		playerTwo = new PlayerTwo();
		gameOver = false;
	}
	
	public void gameLoop() {

		//long = G1G2G3G4G5G60 F1F2F3F4F5F60 E1E2E3E4E5E60 D1D2D3D4D5D60 C1C2C3C4C5C60 B1B2B3B4B5B60 A1A2A3A4A5A60 0000000 0000000 0
			
		
		gui.playerMoveProperty().addListener(new ChangeListener<Object>() {
			@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				playerOne.setIsTurnProperty();
				playerTwo.setIsTurnProperty();
			}
		});
		
		playerOne.isTurnProperty().addListener(new ChangeListener<Object>() {
			@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				if(playerOne.isTurnProperty().getValue() == false) {
					long boardMovement = 0b1L << gui.playerMoveProperty().longValue();
					System.out.println(playerOne.isTurnProperty());
					gui.updateBoard(boardMovement, 1);
				}
			}
		});
		
		playerTwo.isTurnProperty().addListener(new ChangeListener<Object>() {
			@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				if(playerTwo.isTurnProperty().getValue() == false) {
					long boardMovement = 0b1L << gui.playerMoveProperty().longValue();
					System.out.println(playerTwo.isTurnProperty());
					gui.updateBoard(boardMovement, 2);
				}
			}
		});
		
	}

	public Scene getScene() {
		return gui.getScene();
	}
}
