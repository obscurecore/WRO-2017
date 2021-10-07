
public  class Ranking {
	
//	static String[][] real2;
	static int q = 0;
	static String[][][] limcomp = new String[32][][];
//	static Ranking[] rks = new Ranking[32];
	
//	static boolean exLoop = false;
//	static int exFULLCount = 0; //number of launching exitLoop()
//	static int exCount = -1;	// number current return value in =  way2ex[exFULLCount][exCount]
	
//	static int[] ranks = new int[4];
	static char[] ranks = new char[4];
	static int repeAT = 0;
//	static char[][] way2ex = new char[32][];
//	static int count_Launches = 0;
//	static int count_mapChanges = 0;
	
	 static boolean isConstructor = false;
	
	
	public Ranking(int northRank, int southRank, int westRank, int eastRank, String[][] real) {

		// choose the best dir for the move, change value to 1(best),2(good),3(no bad),4(shit)
		isConstructor = true;
		ranks[southRank] = 's';
		ranks[northRank] = 'n';
		ranks[eastRank] = 'e';
		ranks[westRank] = 'w';
		limcomp[q] = real;
//		ranks[0] = westRank;
//		ranks[1] = northRank;
//		ranks[2] = eastRank;
//		ranks[3] = southRank;
//		real2 = real;
//		rks[q].limcomp = real;
	}

	public Ranking() {
		//for exit from Everlasting loop
//		int coordX;
//		int coordY;
	}

	public static char rankCompare(Command cmd, int[] walls){
		//char dir, int[] walls, String[][] real, int currX, int currY
		
		// walls - array of walls	  1	  	
		//							0 	2	
		//							  3
		//real = array , thich create when robot move
		// ��������� �����, ��� �� ����� ���, �� "�"
		//check all map, with goal dont return to passed cell
		// return dir
		
		
		// need to do test if robot begin move in limitless loop
		// if real map dont change for long time (5-6 moves)
		
//		if (exLoop) {
//			if (exCount != (way2ex.length-2)) {
//				exCount++;
//				return way2ex[exFULLCount][exCount];
//			}
//			else {
//				exFULLCount++;
//				exCount = -1;
//				exLoop = false;
//				repeAT = 0;
//			//	return way2ex[exFULLCount][exCount];
//			}
//		}
		
		if (!isConstructor && repeAT == 0) {
		//	rks[q].limcomp = cmd.real;
			// isConstructor = true;
			limcomp[q] = cmd.real;
			autoRanking(cmd.dir);
		}
		
		if (repeAT > 5) {
			//mapChng(cmd.real, limcomp[q]);
			if (mapChng(cmd.real)) {
				repeAT = 0;
				q++;
				mapRewrite(cmd.real);
			}
			else {
				repeAT = 0;
				System.out.println("Error: Everlasting loop");
			
				char[] way2ex = exitLoop(cmd);
				// cmd.currCoords[1], cmd.currCoords[0], cmd.real
				for (int i = 0; i < (way2ex.length - 1); i++) { // -1 because we go to cell, which will before necessary cell, and return command which move our to target cell
					Command.CommandFunc(way2ex[i], cmd.dir);
				}
				return way2ex[(way2ex.length-1)];
			}
		}
		repeAT++;
		
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += walls[i];
		}
		
		int dopi = 5;
		if (sum == 0) {
			for (int i = 0; i < 3; i++) {
			//	if (!was(dir, i, real, currX, currY)) {
				if (!was(cmd.dir, i, cmd.real, cmd.currCoords[1], cmd.currCoords[0])) {
					if (dopi == 5) {
						dopi = i;
					}
					else if (getPrefer(cmd.dir, i) < getPrefer(cmd.dir, dopi)) {
						dopi = i;
					}
				}
			}
			if (dopi == 5) { // there we can launch exitLoop() !!!
				dopi = 0;
				for (int i = 1; i < 4; i++) {
					if (getPrefer(cmd.dir, i) < getPrefer(cmd.dir, dopi)) {
						dopi = i;
					}
				}
			}
			return pickDir(dopi, cmd.dir);
		}
		if (sum == 1) {
			for (int i = 0; i < 4; i++) {
			//	if (walls[i] == 0 & !was(dir, i, real, currX, currY)) {
				if (walls[i] == 0 & !was(cmd.dir, i, cmd.real, cmd.currCoords[1], cmd.currCoords[0])) {
					if (dopi == 5) {
						dopi = i;
					}
					else if (getPrefer(cmd.dir, i) < getPrefer(cmd.dir, dopi)) {
						dopi = i;
					}
				}
			}
			if (dopi == 5) { // there we can launch exitLoop() !!!
				dopi = 0;
				for (int i = 1; i < 4; i++) {
					if (walls[i] == 0) {
						if (getPrefer(cmd.dir, i) < getPrefer(cmd.dir, dopi)) {
							dopi = i;
						}
					}
				}
			}
			return pickDir(dopi, cmd.dir);
		}
		
		if (sum == 2) {
			for (int i = 0; i < 4; i++) {
			//	if (walls[i] == 0 & !was(dir, i, real, currX, currY)) {
				if (walls[i] == 0 & !was(cmd.dir, i, cmd.real, cmd.currCoords[1], cmd.currCoords[0])) {
					if (dopi == 5) {
						dopi = i;
					}
					else if (getPrefer(cmd.dir, i) < getPrefer(cmd.dir, dopi)) {
						dopi = i;
					}
				}
			}
			if (dopi == 5) { // there we can launch exitLoop() !!!
				dopi = 0;
				for (int i = 1; i < 4; i++) {
					if (walls[i] == 0) {
						if (getPrefer(cmd.dir, i) < getPrefer(cmd.dir, dopi)) {
							dopi = i;
						}
					}
				}
			}
			return pickDir(dopi, cmd.dir);
		}
		
		if (sum == 3) { //we can call exitLoop here
			for (int i = 0; i < 4; i++) {
				if (walls[i] == 0) {
				//	System.out.println("i = " + i);
					return pickDir(i, cmd.dir);
				}
			}
		}
		return '0';
	}
	public static boolean mapChng(String[][] real) {
		for (int i = 0, rl = real.length; i < rl; i++) {
			for (int j = 0, r0l = real[0].length; j < r0l; j++) {
				if (!Command.strCompare(real[i][j], limcomp[q][i][j])) {
					return true;
				}
			}
		}
		return false;
	}
	public static void mapRewrite(String[][] real) {
//		rks[q] = new Ranking();
		limcomp[q] = real;
//		limcomp[q] = new String[real.length][real[0].length];
//		for (int i = 0, rl = real.length; i < rl; i++) {
//			for (int j = 0, r0l = real[0].length; j < r0l; j++) {
//				//rks[q].limcomp[i][j] = real[i][j];
//				limcomp[q][i][j] = real[i][j];
//			}
//		}
	}

	public static char pickDir(int n, char dir) {
		
		switch (dir) {
		case 'n':
			switch (n) {
			case 0:
			//	System.out.println("a");
				return 'a';
			case 1:
			//	System.out.println("w");
				return 'w';
			case 2:
			//	System.out.println("d");
				return 'd';
			case 3:
			//	System.out.println("s");
				return 's';
			}
		case 's':
			switch (n) {
			case 0:
			//	System.out.println("d");
				return 'd';
			case 1:
			//	System.out.println("s");
				return 's';
			case 2:
			//	System.out.println("a");
				return 'a';
			case 3:
			//	System.out.println("w");
				return 'w';
			}
		case 'w':
			switch (n) {
			case 0:
			//	System.out.println("s");
				return 's';
			case 1:
			//	System.out.println("a");
				return 'a';
			case 2:
			//	System.out.println("w");
				return 'w';
			case 3:
			//	System.out.println("d");
				return 'd';
			}
		case 'e':
			switch (n) {
			case 0:
			//	System.out.println("w");
				return 'w';
			case 1:
			//	System.out.println("d");
				return 'd';
			case 2:
			//	System.out.println("s");
				return 's';
			case 3:
			//	System.out.println("a");
				return 'a';
			}
		}
//		switch (n) {
//		case 0:
//			System.out.println("a");
//			return 'a';
//		case 1:
//			System.out.println("w");
//			return 'w';
//		case 2:
//			System.out.println("d");
//			return 'd';
//		case 3:
//			System.out.println("s");
//			return 's';
//		}
		return '0';
	}
	public static char[] exitLoop(Command cmd) {
		//int currX, int currY, String[][] real
//		Ranking[] coords = new Ranking[(real.length-1)*(real[0].length-1)];
			
		int[][] coords = new int[(cmd.real.length-1)*(cmd.real[0].length-1)/4][2];	//because we have array with walls, but we need only cells
		int escCount = 0;
		
		for (int i = 1, rl = cmd.real.length-2; i <= rl; i+=2) {
			for (int j = 1, rlcurr = cmd.real[i].length-2; j <= rlcurr; j+=2) {
				if (cmd.real[i][j] == null) {
					if (cmd.strCompare(cmd.real[i-1][j], "0") || cmd.strCompare(cmd.real[i+1][j], "0") || cmd.strCompare(cmd.real[i][j-1], "0") || cmd.strCompare(cmd.real[i][j+1], "0") ) {
						coords[escCount][0] = i;
						coords[escCount][1] = j;
					}
				}
			}
		}
	
		int memi = 0;
		int mind = MinDistance.Minmain(cmd.real, cmd.currCoords[0], cmd.currCoords[1], coords[0][0], coords[0][1]).length;
		for (int i = 1; i <= escCount; i++) {
			if (mind > MinDistance.Minmain(cmd.real, cmd.currCoords[0], cmd.currCoords[1], coords[i][0], coords[i][1]).length) {
				mind = MinDistance.Minmain(cmd.real, cmd.currCoords[0], cmd.currCoords[1], coords[i][0], coords[i][1]).length;
				memi = i;
			}
		}
		return MinDistance.Minmain(cmd.real, cmd.currCoords[0], cmd.currCoords[1], coords[memi][0], coords[memi][1]);
	}
	public static int getPrefer(char dir, int side) {
		//getting value of preference this side
		char d = dir;
		switch(dir) {
		case 'n':
			switch(side) {
			case 0: d = 'w'; break;
			case 1: d = 'n'; break;
			case 2: d = 'e'; break;
			case 3: d = 's'; break;
			}
		case 's':
			switch(side) {
			case 0: d = 'e'; break;
			case 1: d = 's'; break;
			case 2: d = 'w'; break;
			case 3: d = 'n'; break;
			}
		case 'w':
			switch(side) {
			case 0: d = 's'; break;
			case 1: d = 'w'; break;
			case 2: d = 'n'; break;
			case 3: d = 'e'; break;
			}
		case 'e':
			switch(side) {
			case 0: d = 'n'; break;
			case 1: d = 'e'; break;
			case 2: d = 's'; break;
			case 3: d = 'w'; break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (ranks[i] == d) {
				return i;
			}
		}
		System.out.println("Error in Ranking, getPrefer");
		return 5;
	}
	public static void autoRanking(char dir) {
		switch (dir) {
		case 'n':
			ranks[0] = 'n';
			ranks[1] = 'e';
			ranks[2] = 'w';
			ranks[3] = 's';
		case 's':
			ranks[0] = 's';
			ranks[1] = 'e';
			ranks[2] = 'w';
			ranks[3] = 's';
		case 'w':
			ranks[0] = 'w';
			ranks[1] = 'n';
			ranks[2] = 's';
			ranks[3] = 'e';
		case 'e':
			ranks[0] = 'e';
			ranks[1] = 'n';
			ranks[2] = 's';
			ranks[3] = 'w';
		}
//		switch (dir) {
//		case 'n':
//			ranks[0] = 2;
//			ranks[1] = 1;
//			ranks[2] = 3;
//			ranks[3] = 4;
//			break;
//		case 'w':
//			ranks[0] = 1;
//			ranks[1] = 3;
//			ranks[2] = 4;
//			ranks[3] = 2;
//			break;
//		case 'e':
//			ranks[0] = 4;
//			ranks[1] = 2;
//			ranks[2] = 1;
//			ranks[3] = 3;
//			break;
//		case 's':
//			ranks[0] = 4;
//			ranks[1] = 3;
//			ranks[2] = 2;
//			ranks[3] = 1;
//			break;
//		}
	}
	public static boolean was(char dir, int n, String[][] real, int currX, int currY) {
		// there we can change so many compares on compare with null if it will work
		// example: if (real[y][x] != null) ...... 
		switch (dir) {
		case 'n':
			switch (n) {
			case 0:
				if (currX > 2) {
					if (real[currY][currX - 2] != null) {
						return true;
					}
					return false;
				}
			case 1:
				if (currY > 2) {
					if (real[currY - 2][currX] != null) {
						return true;
					}
					return false;
				}
			case 2:
				if (currX < real[0].length - 3) {
					if (real[currY][currX + 2] != null) {
						return true;
					}
					return false;
				}
			case 3:
				if (currY < real.length - 3) {
					if (real[currY + 2][currX] != null) {
						return true;
					}
					return false;
				}
			}
		case 's':
			switch (n) {
			case 0:
				if (currX < real[0].length - 3) {
					if (real[currY][currX + 2] != null) {
						return true;
					}
					return false;
				}
			case 1:
				if (currY < real.length - 3) {
					if (real[currY + 2][currX] != null) {
						return true;
					}
					return false;
				}
			case 2:
				if (currX > 2) {
					if (real[currY][currX - 2] != null) {
						return true;
					}
					return false;
				}
			case 3:
				if (currY > 2) {
					if (real[currY - 2][currX] != null) {
						return true;
					}
					return false;
				}
			}
		case 'w':
			switch (n) {
			case 0:
				if (currY < real.length - 3) {
					if (real[currY + 2][currX] != null) {
						return true;
					}
					return false;
				}
			case 1:
				if (currX > 2) {
					if (real[currY][currX - 2] != null) {
						return true;
					}
					return false;
				}
			case 2:
				if (currY > 2) {
					if (real[currY - 2][currX] != null) {
						return true;
					}
					return false;
				}
			case 3:
				if (currX < real[0].length - 3) {
					if (real[currY][currX + 2] != null) {
						return true;
					}
					return false;
				}
			}
		case 'e':
			switch (n) {
			case 0:
				if (currY > 2) {
					if (real[currY - 2][currX] != null) {
						return true;
					}
					return false;
				}
			case 1:
				if (currX < real[0].length - 3) {
					if (real[currY][currX + 2] != null) {
						return true;
					}
					return false;
				}
			case 2:
				if (currY < real.length - 3) {
					if (real[currY + 2][currX] != null) {
						return true;
					}
					return false;
				}
			case 3:
				if (currX > 2) {
					if (real[currY][currX - 2] != null) {
						return true;
					}
					return false;
				}
			}
		}
//		switch (dir) {
//		case 'n':
//			switch (n) {
//			case 0:
//				if (currX > 2) {
//					if (Command.strCompare(real[currY][currX - 2], "a") || Command.strCompare(real[currY][currX - 2], "c") || Command.strCompare(real[currY][currX - 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 1:
//				if (currY > 2) {
//					if (Command.strCompare(real[currY - 2][currX], "a") || Command.strCompare(real[currY - 2][currX], "c") || Command.strCompare(real[currY - 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 2:
//				if (currX < real[0].length - 3) {
//					if (Command.strCompare(real[currY][currX + 2], "a") || Command.strCompare(real[currY][currX + 2], "c") || Command.strCompare(real[currY][currX + 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 3:
//				if (currY < real.length - 3) {
//					if (Command.strCompare(real[currY + 2][currX], "a") || Command.strCompare(real[currY + 2][currX], "c") || Command.strCompare(real[currY + 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			}
//		case 's':
//			switch (n) {
//			case 0:
//				if (currX < real[0].length - 3) {
//					if (Command.strCompare(real[currY][currX + 2], "a") || Command.strCompare(real[currY][currX + 2], "c") || Command.strCompare(real[currY][currX + 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 1:
//				if (currY < real.length - 3) {
//					if (Command.strCompare(real[currY + 2][currX], "a") || Command.strCompare(real[currY + 2][currX], "c") || Command.strCompare(real[currY + 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 2:
//				if (currX > 2) {
//					if (Command.strCompare(real[currY][currX - 2], "a") || Command.strCompare(real[currY][currX - 2], "c") || Command.strCompare(real[currY][currX - 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 3:
//				if (currY > 2) {
//					if (Command.strCompare(real[currY - 2][currX], "a") || Command.strCompare(real[currY - 2][currX], "c") || Command.strCompare(real[currY - 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			}
//		case 'w':
//			switch (n) {
//			case 0:
//				if (currY < real.length - 3) {
//					if (Command.strCompare(real[currY + 2][currX], "a") || Command.strCompare(real[currY + 2][currX], "c") || Command.strCompare(real[currY + 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 1:
//				if (currX > 2) {
//					if (Command.strCompare(real[currY][currX - 2], "a") || Command.strCompare(real[currY][currX - 2], "c") || Command.strCompare(real[currY][currX - 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 2:
//				if (currY > 2) {
//					if (Command.strCompare(real[currY - 2][currX], "a") || Command.strCompare(real[currY - 2][currX], "c") || Command.strCompare(real[currY - 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 3:
//				if (currX < real[0].length - 3) {
//					if (Command.strCompare(real[currY][currX + 2], "a") || Command.strCompare(real[currY][currX + 2], "c") || Command.strCompare(real[currY][currX + 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			}
//		case 'e':
//			switch (n) {
//			case 0:
//				if (currY > 2) {
//					if (Command.strCompare(real[currY - 2][currX], "a") || Command.strCompare(real[currY - 2][currX], "c") || Command.strCompare(real[currY - 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 1:
//				if (currX < real[0].length - 3) {
//					if (Command.strCompare(real[currY][currX + 2], "a") || Command.strCompare(real[currY][currX + 2], "c") || Command.strCompare(real[currY][currX + 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 2:
//				if (currY < real.length - 3) {
//					if (Command.strCompare(real[currY + 2][currX], "a") || Command.strCompare(real[currY + 2][currX], "c") || Command.strCompare(real[currY + 2][currX], "x")) {
//						return true;
//					}
//					return false;
//				}
//			case 3:
//				if (currX > 2) {
//					if (Command.strCompare(real[currY][currX - 2], "a") || Command.strCompare(real[currY][currX - 2], "c") || Command.strCompare(real[currY][currX - 2], "x")) {
//						return true;
//					}
//					return false;
//				}
//			}
//		}
		return false;
	}
}
