package connectFour;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

public class GameController {
	
	private GUI gui;
	private PlayerOne playerOne;
	private PlayerTwo playerTwo;
	private boolean gameOver;
	private ChangeListener<Object> playerOneListener;
	private ChangeListener<Object> playerTwoListener;
	private ChangeListener<Object> GUIListener;

	
	public GameController() {
		gui = new GUI();
		playerOne = new PlayerOne();
		playerTwo = new PlayerTwo();
		gameOver = false;	
	}
	
	public void gameLoop() {

		//long = G1G2G3G4G5G60 F1F2F3F4F5F60 E1E2E3E4E5E60 D1D2D3D4D5D60 C1C2C3C4C5C60 B1B2B3B4B5B60 A1A2A3A4A5A60 0000000 0000000 0
			
			
			gui.playerMoveProperty().addListener(GUIListener = new ChangeListener<Object>() {
				@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
					playerOne.setIsTurnProperty();
					playerTwo.setIsTurnProperty();
				}
			});
			
			playerOne.isTurnProperty().addListener(playerOneListener = new ChangeListener<Object>() {
				@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
					if(playerOne.isTurnProperty().getValue() == false) {
						long binaryValue = (long) Math.pow(2, gui.playerMoveProperty().get());
						
						System.out.println("Binary Value: " + binaryValue + " " + Long.toBinaryString(binaryValue));
						
						long playerMove = playerOne.movePlayer(binaryValue, playerOne.getBitBoard(), playerTwo.getBitBoard());
						
						if(playerMove > 0) {
							playerOne.setBitBoard(playerMove);
							System.out.println("Player one: " + Long.toBinaryString(playerOne.getBitBoard()));
							gui.updateBoard(binaryValue, 1);
							gameOver = playerOne.checkWin(playerOne.getBitBoard());
						}
					}
					
					if(gameOver == true) {
						endGame();
					}
				}
			});
			
			playerTwo.isTurnProperty().addListener(playerTwoListener = new ChangeListener<Object>() {
				@Override public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
					if(playerTwo.isTurnProperty().getValue() == false) {
						long binaryValue = (long) Math.pow(2, gui.playerMoveProperty().get());
						
						long playerMove = playerOne.movePlayer(binaryValue, playerTwo.getBitBoard(), playerOne.getBitBoard());
						
						if(playerMove > 0) {
							playerTwo.setBitBoard(playerMove);
							System.out.println("Player two: " + Long.toBinaryString(playerTwo.getBitBoard()));
							gui.updateBoard(binaryValue, 2);
							gameOver = playerTwo.checkWin(playerTwo.getBitBoard());
						}
						 
					}
					
					if(gameOver == true) {
						endGame();
					}
				}
			});
		
	}
	
	private void endGame() {
		playerOne.isTurnProperty().removeListener(playerOneListener);
		playerTwo.isTurnProperty().removeListener(playerTwoListener);
		gui.playerMoveProperty().removeListener(GUIListener);
		System.out.println("\nGAME OVER!");
	}
	

	public Scene getScene() {
		return gui.getScene();
	}
}
