package connectFour;

public abstract class BitBoard {
	
	/* A bit board corresponds to a long in the following way:   
	 * 
	 * 	 				 0  0  0  0  0  0  0  0 0<--Right Most Bit
	 * 	 				 G6 F6 E6 D6 C6 B6 A6 0 0<--TOP OF VISIBLE GAME BOARD
	 * 	 				 G5 F5 E5 D5 C5 B5 A5 0 0 
	 * 	 				 G4 F4 E4 D4 C4 B4 A4 0 0   
	 * 	 				 G3 F3 E3 D3 C3 B3 A3 0 0  
	 * 	 				 G2 F2 E2 D2 C2 B2 A2 0 0   
	 * Left most bit-->0 G1 F1 E1 D1 C1 B1 A1 0 0<--BOTTOM OF VISIBLE GAME BOARD
	 * 
	 * 					  56 49 42 35 28 21 14 07 00<--BUFFER/NO ACCESS/Right Most Bit
	 * 					  57 50 43 36 29 22 15 08 01<--TOP OF VISIBLE GAME BOARD
	 * 					  58 51 44 37 30 23 16 09 02
	 * 					  59 52 45 38 31 24 17 10 03  
	 * 					  60 53 46 39 32 25 18 11 04  
	 * 					  61 54 47 40 33 26 19 12 05  
	 * Left most bit-->63 62 55 48 41 34 27 20 13 06 <--BOTTOM OF VISIBLE GAME BOARD
	 * 
	 * long = 0G1G2G3G4G5G60 F1F2F3F4F5F60 E1E2E3E4E5E60 D1D2D3D4D5D60 C1C2C3C4C5C60 B1B2B3B4B5B60 A1A2A3A4A5A60 0000000 000000(0) 
	 */

	public long movePlayer(long tempBoard, long bitBoard1, long bitBoard2) {
		long finalBoard = bitBoard1 ^ bitBoard2;
		
		tempBoard = drop(tempBoard, finalBoard);
		
		long tempBoard2 = tempBoard | finalBoard;
		
		if(tempBoard2 != finalBoard) {
			return tempBoard;
		}
		else
			return 0L;
	}
	
	private long drop(long tempBoard, long finalBoard) {

		if(tempBoard <= 4611686018427387904L && tempBoard >= 144115188075855872L) {
			finalBoard = finalBoard & 0b1111110000000000000000000000000000000000000000000000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b100000000000000000000000000000000000000000000000000000000000000L;
		}
		else if(tempBoard <= 36028797018963968L && tempBoard >= 1125899906842624L) {
			finalBoard = finalBoard & 0b111111000000000000000000000000000000000000000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b10000000000000000000000000000000000000000000000000000000L;
		}
		else if(tempBoard <= 281474976710656L && tempBoard >= 8796093022208L) {
			finalBoard = finalBoard & 0b11111100000000000000000000000000000000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b1000000000000000000000000000000000000000000000000L;
		}
		else if(tempBoard <= 2199023255552L && tempBoard >= 68719476736L) {
			finalBoard = finalBoard & 0b1111110000000000000000000000000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b100000000000000000000000000000000000000000L;
		}
		else if(tempBoard <= 17179869184L && tempBoard >= 536870912L) {
			finalBoard = finalBoard & 0b11111100000000000000000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b10000000000000000000000000000000000L;
		}
		else if(tempBoard <= 134217728L && tempBoard >= 4194304L) {
			finalBoard = finalBoard & 0b1111110000000000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b1000000000000000000000000000L;
		}
		else if(tempBoard <= 1048576L && tempBoard >= 32768L) {
			finalBoard = finalBoard & 0b111111000000000000000L;
			
			if(finalBoard == 0)
				return tempBoard = 0b100000000000000000000L;
		}
		
		long lowest = Long.lowestOneBit(finalBoard) >> 1;
		
		return lowest;
	}

	public boolean checkWin(long bitBoard) {
		boolean isAcross = checkAcross(bitBoard);
		boolean isUpDown = checkUpDown(bitBoard);
		boolean isDiagnol = checkDiagnol(bitBoard);
		boolean isDiagnol2 = checkDiagnol2(bitBoard);
		
		if(isAcross == true)
			return true;
		else if(isDiagnol == true)
			return true;
		else if(isDiagnol2 == true)
			return true;
		else if(isUpDown == true)
			return true;
		else
			return false;	
	} 

	private boolean checkAcross(long bitBoard) {

		long tempBoard = bitBoard >>> 7;
		
		tempBoard = tempBoard & bitBoard;
		
		for(int x = 0; x < 2; x++) {
			tempBoard = tempBoard >>> 7;
			tempBoard = tempBoard & bitBoard;
		}

		if(tempBoard > 0)
			return true;
		else
			return false;

	}
	
	private boolean checkDiagnol(long bitBoard) {
		
		long tempBoard = bitBoard >>> 8;
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
	
	private boolean checkDiagnol2(long bitBoard) {
		
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
