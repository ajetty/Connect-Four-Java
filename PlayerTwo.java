package connectFour;

public class PlayerTwo extends BitBoard{

	private long bitBoard;
	
	public PlayerTwo() {
		this.setBitBoard(0b0L);
	}

	public long getBitBoard() {
		return bitBoard;
	}

	public void setBitBoard(long bitBoard) {
		this.bitBoard = bitBoard;
	}
}
