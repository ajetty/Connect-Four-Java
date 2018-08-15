package connectFour;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PlayerOne extends BitBoard{
	
	private long bitBoard;
	private BooleanProperty isTurnOver;
	private boolean turnValue;
	
	public PlayerOne() {
		this.turnValue = true;
		this.isTurnOver = new SimpleBooleanProperty();
		this.setBitBoard(0b0L);
		this.isTurnOver.set(turnValue);
	}

	public long getBitBoard() {
		return bitBoard;
	}

	public void setBitBoard(long bitBoard) {
		this.bitBoard = bitBoard;
	}
	
	public void setIsTurnProperty() {
		isTurnOver.set(turnValue = !turnValue);
	}
	
	public BooleanProperty isTurnProperty() {
		return isTurnOver;
	}

}
