package connectFour;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GUI {

	private GridPane gameBoard;
	private Scene scene;
	private ArrayList<Group> boardSquares;
	private IntegerProperty playerMoveProperty;
	
	public GUI() {
		gameBoard = setBoard();
		playerMoveProperty = new SimpleIntegerProperty();
		scene = new Scene(gameBoard, 600, 600);
		eventHandler();
	}
	
	private GridPane setBoard() {
		GridPane tempBoard = new GridPane();
		
		tempBoard.setAlignment(Pos.CENTER);
		
		boardSquares = new ArrayList<Group>();
		
		for(int x = 0; x < 42; x++) {
			Circle circle = new Circle();
			circle.setRadius(25);
			circle.setCenterX(40);
			circle.setCenterY(40);
			circle.setFill(Color.DARKGREY);
			
			Rectangle rectangle = new Rectangle();
			rectangle.setWidth(80);
			rectangle.setHeight(80);
			rectangle.setFill(Color.BLACK);
			
			boardSquares.add(new Group());
			boardSquares.get(boardSquares.size()-1).getChildren().addAll(rectangle, circle);
			
		}
		
		createBitIdIndex();
		
		int column = 0;
		int row = 0;
		
		for(Group x : boardSquares) {
			tempBoard.add(x, column, row);
			row++;
			if(row == 6) {
				row = 0;
				column++;
			}
		}

		return tempBoard;
	}
	
	
	/*
	 * int[] bitIndex elements correspond to game board move positions and bitboards used to
	 * record player data.
	 * 
	 * 56 49 42 35 28 21 14 <--BUFFER/NO ACCESS
	 * 57 50 43 36 29 22 15 <--TOP
	 * 58 51 44 37 30 23 16 
	 * 59 52 45 38 31 24 17   
	 * 60 53 46 39 32 25 18   
	 * 61 54 47 40 33 26 19   
	 * 62 55 48 41 34 27 20 <--BOTTOM
	 */
	private void createBitIdIndex() {
		int[] bitIndex = {57, 58, 59, 60, 61, 62, 50, 51, 52, 53, 54, 55, 43, 44, 45, 46, 47, 48, 36, 37, 38, 39, 40, 41, 29, 30, 31, 32, 33, 34, 22, 23, 24, 25, 26, 27, 15, 16, 17, 18, 19, 20};
		
		for(int x = 0; x < bitIndex.length; x++) {
			boardSquares.get(x).getChildren().get(1).setId(Integer.toString(bitIndex[x]));
		}
	}
	
	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		   @Override 
		   public void handle(MouseEvent e) { 
		      if(e.getEventType() == MouseEvent.MOUSE_CLICKED){
		    	  Circle circle = (Circle) e.getSource();
		    	  setPlayerProperty(Integer.parseInt(circle.getId()));
		      }
		      if(e.getEventType() == MouseEvent.MOUSE_ENTERED){
		    	  ((Shape) e.getTarget()).setStrokeWidth(5);
		    	  ((Shape) e.getTarget()).setStroke(Color.BLUE);
		      }
		      if(e.getEventType() == MouseEvent.MOUSE_EXITED){
		    	  ((Shape) e.getTarget()).setStroke(null);
		      }
		   } 
	};
	
	public void eventHandler() {
		for(Group x : boardSquares) {
			x.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
			x.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_ENTERED, eventHandler);
			x.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_EXITED, eventHandler);
		}
	}
	
	public void updateBoard(long bitBoard, int player) {
		String zeroes = Long.toString(Long.numberOfTrailingZeros(bitBoard));
		
		int index = 0;
		
		for(Group x : boardSquares) {
			if(x.getChildren().get(1).getId().equals(zeroes)) {
				index = boardSquares.indexOf(x);	
			}
		}

		//Each Group Id in ArrayList matches with index. 
		if(player == 1) {
			//We're getting the children of Group. 0 = rectangle 1 = circle
			((Shape) boardSquares.get(index).getChildren().get(1)).setFill(Color.GREEN);
		}
		else {
			((Shape) boardSquares.get(index).getChildren().get(1)).setFill(Color.RED);
		}		
	}
	
	//Takes the int from int[] bitIndex and turns the value into a long that is set as the value of LongProperty playerProperty.
	private void setPlayerProperty(int value) {
		playerMoveProperty.setValue(value);
	}
	
	public IntegerProperty playerMoveProperty() {
		return playerMoveProperty;
	}
	

	public Scene getScene() {
		return scene;
	}
}
