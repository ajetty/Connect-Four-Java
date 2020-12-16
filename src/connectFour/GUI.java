package connectFour;


import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class GUI {

    private StackPane imageBoard;
    private GridPane gameBoard;
    private Scene scene;
    private Text gameOverTxt;
    public Button restartBtn;
    private VBox menu;
    private ArrayList<Group> boardSquares;
    private IntegerProperty playerMoveProperty;
    private BooleanProperty isBoardChange;
    private boolean boardChange;
    private int playerTurn;

    //load flower images
    Image redFlowerImage = new Image(getClass().getResourceAsStream("images/redflower.png"));
    Image daisyImage = new Image(getClass().getResourceAsStream("images/daisy.png"));

    public GUI() {
        this.gameBoard = setBoard();
        this.playerMoveProperty = new SimpleIntegerProperty();
        this.isBoardChange = new SimpleBooleanProperty();
        this.scene = new Scene(imageBoard, 950, 600);
        scene.getStylesheets().add("connectFour/connectFour.css");
        eventHandler();
        playerTurn = 2;
    }

    private GridPane setBoard() {

        createBottomMenu();

        imageBoard = new StackPane();

        GridPane tempBoard = new GridPane();

        tempBoard.setAlignment(Pos.CENTER);

        boardSquares = new ArrayList<Group>();

        Image backgroundImage = new Image(getClass().getResourceAsStream("images/flowerbackground.png"));

        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitHeight(550.0);
        imageView.setFitWidth(750.0);
        imageView.setPreserveRatio(false);

        Region backgroundColor = new Region();
        backgroundColor.setId("backgroundColor");


        for (int x = 0; x < 42; x++) {

            Circle circle = new Circle();
            circle.setRadius(25);
            circle.setCenterX(40);
            circle.setCenterY(40);
            circle.setFill(Color.DARKGREY);
            circle.setOpacity(0.7);

            Rectangle rectangle = new Rectangle(0, 0, 80, 80);
            rectangle.setOpacity(0.0);

            boardSquares.add(new Group());
            boardSquares.get(boardSquares.size() - 1).getChildren().addAll(rectangle, circle);

        }

        createBitIdIndex();

        int column = 0;
        int row = 0;

        for (Group x : boardSquares) {
            tempBoard.add(x, column, row);
            row++;
            if (row == 6) {
                row = 0;
                column++;
            }
        }

        //setup HBox to keep grid and menu side by side horizontally
        HBox boardAndMenu = new HBox();
        boardAndMenu.getChildren().addAll(tempBoard, menu);
        boardAndMenu.setAlignment(Pos.CENTER);

        imageBoard.setAlignment(Pos.CENTER);

        imageBoard.getChildren().addAll(backgroundColor, imageView, boardAndMenu);

        //inset -> new Insets(top, right, bottom, left)
        imageBoard.setMargin(imageView, new Insets(0.0, 150.0, 0.0, 0.0));
        imageBoard.setMargin(backgroundColor, new Insets(0.0, 150.0, 0.0, 0.0));

        return tempBoard;
    }

    private void createBottomMenu() {
        menu = new VBox(20);

        menu.setAlignment(Pos.CENTER);
        menu.setId("menu");
        menu.setMinWidth(300.0);
        menu.setMaxHeight(250.0);

        gameOverTxt = new Text();
        gameOverTxt.setText("Please click on a column\nto drop a player's piece.\nThe first one to make four\n in a row wins!");
        gameOverTxt.setStyle("-fx-font-family: Pacifico Regular; -fx-font-size: 20px; -fx-fill: #af2b37");

        restartBtn = new Button("Play Again");

        menu.getChildren().addAll(gameOverTxt, restartBtn);
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

        for (int x = 0; x < bitIndex.length; x++) {
            boardSquares.get(x).getChildren().get(1).setId(Integer.toString(bitIndex[x]));
        }
    }

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
                Circle circle = (Circle) e.getSource();
                setPlayerProperty(Integer.parseInt(circle.getId()));
                setBoardProperty();
                ((Shape) e.getTarget()).setFill(Color.DARKGREY);
            }
            if (e.getEventType() == MouseEvent.MOUSE_ENTERED) {
                ((Shape) e.getTarget()).setStrokeWidth(1);
                ((Shape) e.getTarget()).setStroke(Color.BLUE);
                if(playerTurn == 1) {
                    ((Shape) e.getTarget()).setFill(new ImagePattern(daisyImage, 18, 18, 45, 45, false));
                }
                else {
                    ((Shape) e.getTarget()).setFill(new ImagePattern(redFlowerImage, 17, 17, 46, 46, false));
                }
            }
            if (e.getEventType() == MouseEvent.MOUSE_EXITED) {
                ((Shape) e.getTarget()).setStroke(null);
                ((Shape) e.getTarget()).setFill(Color.DARKGREY);
            }

        }
    };

    public void eventHandler() {
        for (Group x : boardSquares) {
            x.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            x.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_ENTERED, eventHandler);
            x.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_EXITED, eventHandler);
        }
    }

    public void updateBoard(long boardMove, int player) {


        int boardMove2 = Long.numberOfTrailingZeros(boardMove);

        String move = Integer.toString(boardMove2);

        int index = 0;

        for (Group x : boardSquares) {
            if (x.getChildren().get(1).getId().equals(move)) {
                index = boardSquares.indexOf(x);
            }
        }

        //Each Group Id in ArrayList matches with index.
        if (player == 1) {
            //We're getting the children of Group that is in the array boardsquares. 0 = rectangle 1 = circle then changing it to an imageview flower
            ImageView daisyImageView = new ImageView(daisyImage);
            daisyImageView.setX(16.0);
            daisyImageView.setY(16.0);
            daisyImageView.setFitHeight(50.0);
            daisyImageView.setFitWidth(50.0);
            daisyImageView.setPreserveRatio(true);
            Circle circle = new Circle(40, 40, 25);
            String id = boardSquares.get(index).getChildren().get(1).getId();
            daisyImageView.setClip(circle);
            daisyImageView.setId(id);
            boardSquares.get(index).getChildren().set(1, daisyImageView);
            playerTurn = 2;
        } else {
            ImageView redFlowerImageView = new ImageView(redFlowerImage);
            redFlowerImageView.setX(16.0);
            redFlowerImageView.setY(16.0);
            redFlowerImageView.setFitHeight(50.0);
            redFlowerImageView.setFitWidth(50.0);
            redFlowerImageView.setPreserveRatio(true);
            Circle circle = new Circle(40, 40, 25);
            String id = boardSquares.get(index).getChildren().get(1).getId();
            redFlowerImageView.setClip(circle);
            redFlowerImageView.setId(id);
            boardSquares.get(index).getChildren().set(1, redFlowerImageView);
            playerTurn = 1;
        }

    }

    public void gameOver() {
        gameOverTxt.setText("Game Over");
        gameOverTxt.setStyle("-fx-font-family: Pacifico Regular; -fx-font-size: 45px; -fx-fill: #af2b37;");
    }

    private void setBoardProperty() {
        isBoardChange.set(boardChange = !boardChange);
    }

    public BooleanProperty isBoardProperty() {
        return isBoardChange;
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
