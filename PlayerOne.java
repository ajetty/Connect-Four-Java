package connectFour;

public class PlayerOne extends BitBoard{
	
	private long bitBoard;
	
	public PlayerOne() {
		this.setBitBoard(0b0L);
	}

	public long getBitBoard() {
		return bitBoard;
	}

	public void setBitBoard(long bitBoard) {
		this.bitBoard = bitBoard;
	}

}
