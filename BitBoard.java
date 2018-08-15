package connectFour;

public abstract class BitBoard {
	
	/* A bit board corresponds to a long in the following way:   
	 * 
	 * 0  0  0  0  0  0  0  0 0
	 * G6 F6 E6 D6 C6 B6 A6 0 0                             TOP
	 * G5 F5 E5 D5 C5 B5 A5 0 0 
	 * G4 F4 E4 D4 C4 B4 A4 0 0   
	 * G3 F3 E3 D3 C3 B3 A3 0 0  
	 * G2 F2 E2 D2 C2 B2 A2 0 0   
	 * G1 F1 E1 D1 C1 B1 A1 0 0 0 <--Right most bit    		BOTTOM
	 * 
	 * 57 50 43 36 29 22 15 8  1
	 * 58 51 44 37 30 23 16 9  2                             TOP
	 * 59 52 45 38 31 24 17 10 3 
	 * 60 53 46 39 32 25 18 11 4   
	 * 61 54 47 40 33 26 19 12 5  
	 * 62 55 48 41 34 27 20 13 6   
	 * 63 56 49 42 35 28 21 14 7 0 <--Right most bit    	BOTTOM
	 * 
	 * long = G1G2G3G4G5G60 F1F2F3F4F5F60 E1E2E3E4E5E60 D1D2D3D4D5D60 C1C2C3C4C5C60 B1B2B3B4B5B60 A1A2A3A4A5A60 0000000 0000000 0 
	 */

	public boolean movePlayer(long tempBoard, long bitBoard1, long bitBoard2) {
		long finalBoard = bitBoard1 ^ bitBoard2;
		
		long tempBoard2 = tempBoard | finalBoard;
		
		if(tempBoard2 != finalBoard) {
			System.out.println("CHECKWIN");
			return checkWin(bitBoard1 ^ tempBoard);
		}
		else
			return false;
	}
	
	public boolean checkWin(long bitBoard) {
		boolean isAcross = checkAcross(bitBoard);
		boolean isUpDown = checkUpDown(bitBoard);
		boolean isDiagnol = checkDiagnol(bitBoard);
		
		if(isAcross == true)
			return true;
		else if(isDiagnol == true)
			return true;
		else if(isUpDown == true)
			return true;
		else
			return false;	
	} 

	private boolean checkAcross(long bitBoard) {

		long tempBoard = bitBoard >> 6;
		
		tempBoard = tempBoard & bitBoard;
		
		for(int x = 0; x < 2; x++) {
			tempBoard = tempBoard >> 6;
			tempBoard = tempBoard & bitBoard;
		}

		if(tempBoard > 0)
			return true;
		else
			return false;

	}
	
	private boolean checkDiagnol(long bitBoard) {
		
		long tempBoard = bitBoard >>> 1;
		tempBoard = tempBoard & bitBoard;
		
		for(int x = 0; x < 2; x++) {
			tempBoard = tempBoard >>> 8;
			tempBoard = tempBoard & bitBoard;
		}
		
		if(tempBoard > 0)
			return true;
		else
			return false;
	}
	
	private boolean checkUpDown(long bitBoard) {
		
		long tempBoard = bitBoard >>> 1;
		tempBoard = tempBoard & bitBoard;
		
		for(int x = 0; x < 2; x++) {
			tempBoard = tempBoard >>> 1;
			tempBoard = tempBoard & bitBoard;
		}

		if(tempBoard > 0)
			return true;
		else
			return false;
		

	}
	
	public long updateBitBoard(long tempBoard, long bitBoard) {
		return tempBoard ^ bitBoard;
	}
	

}
