package connectFour;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
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
	private long playerMove;
	private LongProperty longProperty;
	
	public GUI() {
		gameBoard = setBoard();
		longProperty = new SimpleLongProperty();
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
	
	private void createBitIdIndex() {
		int[] bitIndex = {58, 59, 60, 61, 62, 63, 51, 52, 53, 54, 55, 56, 44, 45, 46, 47, 48, 49, 37, 38, 39, 40, 41, 42, 30, 31, 32, 33, 34, 35, 23, 24, 25, 26, 27, 28, 16, 17, 18, 19, 20, 21};
		
		for(int x = 0; x < bitIndex.length; x++) {
			boardSquares.get(x).getChildren().get(1).setId(Integer.toString(bitIndex[x]));
		}
	}
	
	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		   @Override 
		   public void handle(MouseEvent e) { 
		      if(e.getEventType() == MouseEvent.MOUSE_CLICKED){
		    	  Circle circle = (Circle) e.getSource();
		    	  setPlayerMove(circle.getId());
		    	  getPlayerMove();
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
			if(x.getId().equals(zeroes)) {
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
	
	public void setPlayerMove(String id) {
		this.playerMove = (long) Math.pow(2, Double.parseDouble(id));
	}
	
	public LongProperty getPlayerMove() {
		longProperty.set(playerMove);
		return longProperty;
	}

	public Scene getScene() {
		return scene;
	}
}
