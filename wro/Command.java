//import java.util.Scanner;

//	Changes:
//	Ended:   Part1 - redo all(44 graduates err, implementation. At least test)
// 	Ended:	 if he rotate to wall at 1st time he go forward , in wall
// 	Ended:	 if he go back to cell where he take cube he take him again
// 	Ended: 	 He think that he no was in cell, where he was(Error in write cell type or CngCoords)

// 	exit loop, mindist line:66
//	Way test. Test all moving commands in conjuction
//	Test: Mindist dont work, if we stay at fin cell (startCell == finCell)
// Introduce cngCoords in Main class (when we rotate) or in CommandFunc
// REDO Drive and Main
// Potential error in Main mn object, which initializate in Command (Do Main methods static or initializate Main in Ranking)
// Test real, which create when we move
// Need (exception)decision If he dont see the cube(in finder)
// Need redo the final commands, add condition (when we throw cubes we can built our way to begin cell through warehouse, and we move all cubes to begin cell (need decision))
// Need do uploading
// Ned redo getTarget, getCubes, (if we get one or more cubes)

public class Command {
	
// CUSTOM VARIABLES
	static final boolean compass = true;
	static final int NORTH = 30;
	static final int X = 5;
	static final int Y = 3;
	static final int X_EXT = X*2+1;
	static final int Y_EXT = Y*2+1;
	static final int ALL_CUBES = 3; // count of all cubes on map
	static char beginDir;
	
// DYNAMIC VARIABLES
	static int cubesHave = 0; // count of cubes in robot, that we have
	static String[][] real;
	static int[] currCoords = new int[4];
	static char dir;
	static char sideTurn = 'r'; //side, where our need rotate to know about walls in getInfo ('l' or 'r')
	
	static String[][] map = {{null, "1",null, "1",null, "1",null, "1",null, "1",null},
							 { "1", "a", "0", "c", "0", "a", "1", "c", "0", "a", "1"},
							 {null, "0",null, "0",null, "1",null, "1",null, "0",null},
							 { "1", "a", "1", "a", "1", "c", "0", "a", "0", "a", "1"},
							 {null, "0",null, "0",null, "1",null, "0",null, "0",null},
							 { "1", "a", "0", "a", "0", "a", "0", "a", "0", "x", "1"},
							 {null, "1",null, "1",null, "1",null, "1",null, "1",null}};
								// a - simple cell, c - cell with cube, x - warehouse
	
	
//	static Main mn = new Main();
	
	public static void main(String[] args) {
		
//  PART 0 - Definition all necessary dynamic values
		Command cmd = new Command();
//		Main mn = new Main();
		
		if (compass) {
			real = new String[(Y_EXT*2-3)][(X_EXT*2-3)];
			currCoords[0] = Y_EXT-2; // because we begin consider from 0 index
			currCoords[1] = X_EXT-2;
		}
		else {
			int maxLn = X_EXT >= Y_EXT ? X_EXT: Y_EXT;
			real = new String[(maxLn*2-1)][(maxLn*2-1)];
//			beginCoords[0] = maxLn-1;
//			beginCoords[1] = maxLn-1;
//			currCoords = beginCoords;
			currCoords[0] = maxLn-1; // because we begin consider from 0 index
			currCoords[1] = maxLn-1;
		}
		
//		// coordinats of warehouse cell
//		// we write coords in variable and change it on "a", so that we will compare map with real
		for (int i = 1, il = map.length; i < il; i+= 2) {
			for (int j = 1, jl = map[i].length; j < jl; j+= 2) {
				if (strCompare(map[i][j], "x")) {
					getTarget.end[0] = i;
					getTarget.end[1] = j;
					map[i][j] = "a";
				}
			}
		}
		
//  PART 1 - Definition our initial direction
		int degree = Begin.getDeg(); 
		dir = DirDefine.def(NORTH, degree);
		beginDir = dir;
		System.out.println("Dir = " + dir);
		
		Main.WaitForPress();
//		System.out.println("Current y: " + currCoords[0] + " x:" + currCoords[1]);
//		
////		ALL WORK SOURCE UNDER
////******************************************************************
		
		// check walls on 1st cell, rotate and  change direction//write a in 1st cell, because robot cant stay anymore than "a" cell type
		data d = Main.getWalls(sideTurn);
		WriteInfo(d);
 		real[currCoords[0]][currCoords[1]] = "a";
		
		System.out.println("Dir = " + dir + "  " + d.walls[0] + " " + d.walls[1] + " " + d.walls[2] + " " + d.walls[3]);
		System.out.println("Y : " + currCoords[0] + "; X : " + currCoords[1]);

		CommandFunc(Ranking.rankCompare(cmd, d.walls), dir);
		System.out.println("Y : " + currCoords[0] + "; X : " + currCoords[1]);
//		Main.forward();
// begin 5 - 9
// in real 3 - 9;
// up 2 - left 8
// cube 1 - 3
// i 0 j 2
// i2 4 j2 4
// i3 4 j3 2

// Search.....
		while(Finder.Matches(cmd) != 1) {
			data d2 = Main.getInfo(sideTurn, dir);
			WriteInfo(d2);	
			Main.timeBreak(7000);
			CommandFunc(Ranking.rankCompare(cmd, d2.walls), dir);
			System.out.println("Y : " + currCoords[0] + "; X : " + currCoords[1]);
			System.out.println("Dir = " + dir + "  " + d.walls[0] + " " + d.walls[1] + " " + d.walls[2] + " " + d.walls[3]);
//			Main.forward();

		}
//		Main.turn180();
//		Main.turn180();
		Finder.getCoordinats(cmd);
		
		System.out.println("Finded!!!");
		System.out.println("y = " + currCoords[0] + "; x = " + currCoords[1]);

		// get all Cubes
		char[][] way = getTarget.setTarget(map, currCoords);
		for (int i = 0; i < way.length; i++) {
			for (int j = 0; j < way[i].length; j++) {
				Command.CommandFunc(way[i][j], dir);
				System.out.println("Y : " + currCoords[0] + "; X : " + currCoords[1]);
			}
			if (i != (way.length-1)) {
				Main.getCube();
			}
		}
				
		//Throw cubes
//		Main.throwCubes();
		char[] lastRoad = MinDistance.Minmain(map, currCoords[0], currCoords[1], currCoords[2], currCoords[3]);	
		MoveWithThrow(cmd.dir, lastRoad[0]);
		
		
		//Go to 1st cell
//		char[] lastRoad = MinDistance.Minmain(map, currCoords[0], currCoords[1], currCoords[2], currCoords[3]);
		for (int i = 1; i < lastRoad.length; i++) {
			CommandFunc(lastRoad[i], dir);
		}
		stayInBeginDir(beginDir, dir);
		
		Main.PowerOff();
		
//*****************************************************************
//		MAIN SOURCE ABOVE

		
		
//	MOVING TEST HERE
//		int[] bcord = {2*2+1, 4*2+1};
//		int[] fcord = {1, 3*2+1};
//		currCoords = bcord;
//		char[] in = MinDistance.Minmain(arr, bcord[1], bcord[0], fcord[1], fcord[0], 3, 5);
//		for (int i = 0; i < in.length; i++) {
//			data d = Cumshot.getInfo(turnDir4Inf, dir);	
		//			dir = CommandFunc(in[i], d.dir);
//		//	dir = CommandFunc(in[i], dir);
//			
//			Cumshot.tank();
//			chgCoords(cmd);
//			System.out.println(" y: " + currCoords[0] + " x: " + currCoords[1]);
//		//	System.out.println("Cube: " + d.isCube + " y: " + currCoords[0] + " x: " + currCoords[1]);
//		}
	
		

//		String [][] comp1 = {{null,null,null,null},{null,null,null,"3"},{null,null,"2",null},{null,null,"0",null},
//							{null,null,null,null},{null,null,null,null},{null,null,null,null}};
//				
//		String[][] arr = {{null,"1",null,"1",null,"1",null,"1",null,"1",null,"1",null,"1",null,"1",null},
//						 {"1","b","0","c","0","e","0","b","1","b","0","c","0","c","0","b","1"},
//						 {null,"0",null,"1",null,"0",null,"0",null,"0",null,"1",null,"1",null,"0",null},
//						 {"1","c","0","d","1","c","1","d","1","c","1","x","1","b","0","b","1"},
//						 {null,"0",null,"1",null,"0",null,"1",null,"0",null,"1",null,"0",null,"1",null},
//						 {"1","c","1","x","1","e","0","c","0","e","0","e","0","e","1","d","1"},
//						 {null,"0",null,"1",null,"0",null,"1",null,"1",null,"0",null,"0",null,"0",null},
//						 {"1","b","0","b","0","e","0","c","0","c","0","b","1","b","0","b","1"},
//						 {null,"1",null,"1",null,"1",null,"1",null,"1",null,"1",null,"1",null,"1",null}};
//	 a - simple cell, c - cell with cube, x - last cell
			
		
	}
	public static void CommandFunc(char command, char dir) {
		/*command - from commands array
		 * 	 N			W
		 * W   E	  A S D
		 * 	 S
		 */
//	 		Here realize turn for next move
			switch (command) {
			case 'w':
				switch (dir) {
				case 'n': Main.forward(); break;
				case 's': System.out.println("Turn 180"); Main.turn180(); Main.forward(); break;
				case 'w': System.out.println("Right"); Main.right90(); Main.forward(); break;
				case 'e': System.out.println("Left"); Main.left90(); Main.forward(); break;
				}
				break;
			case 's':
				switch (dir) {
				case 'n': System.out.println("Turn 180"); Main.turn180(); Main.forward(); break;
				case 's': Main.forward(); break;
				case 'w': System.out.println("Left"); Main.left90(); Main.forward(); break;
				case 'e': System.out.println("Right"); Main.right90(); Main.forward(); break;
				}
				break;
			case 'a':
				switch (dir) {
				case 'n': System.out.println("Left"); Main.left90(); Main.forward(); break;
				case 's': System.out.println("Right"); Main.right90(); Main.forward(); break;
				case 'w': Main.forward(); break;
				case 'e': System.out.println("Turn 180"); Main.turn180(); Main.forward(); break;
				}
				break;
			case 'd':
				switch (dir) {
				case 'n': System.out.println("Right"); Main.right90(); Main.forward(); break;
				case 's': System.out.println("Left"); Main.left90(); Main.forward(); break;
				case 'w': System.out.println("Turn 180"); Main.turn180(); Main.forward(); break;
				case 'e': Main.forward(); break;
				}
				break;
			case 'n':
				//Doesn't do anything
			}
		}

	public static void chgCoords(Command n, char cmd) {
		// need change only if we move forward or back
		//cmd - forward or back(b or f)
		if (cmd == 'f') {
			switch (n.dir) {
			case 'n':	n.currCoords[0] -= 2; break;
			case 's':	n.currCoords[0] += 2; break;
			case 'w':	n.currCoords[1] -= 2; break;
			case 'e':	n.currCoords[1] += 2; break;
				default : System.out.println("Error in chgCoords %wrong values in cmd");
			}
		}
		else if (cmd == 'b') {
			switch (n.dir) {
			case 'n':	n.currCoords[0] += 2; break;
			case 's':	n.currCoords[0] -= 2; break;
			case 'w':	n.currCoords[1] += 2; break;
			case 'e':	n.currCoords[1] -= 2; break;
				default : System.out.println("Error in chgCoords %wrong values in cmd");
			}
		}
		else {
			System.out.println("Error in chgCoords %wrong values in cmd");
		}
	}
	public static boolean strCompare(String f1, String f2) {
//		if (f1 == null & f2 == null) {
//			return true;
//		}
//		if (f1 == null || f2 == null) {
//			return false; //or true
//		}
		if (f1 == null && f2 == null) {
			return true;
		}
		if (f1 == null || f2 == null) {
			return false;
		}
		if (f1.length() != f2.length()) {
			return false;
		}
		int dopf = f1.length();
		for (int i = 0; i < dopf; i++) {
			if (f1.charAt(i) != f2.charAt(i)){
				return false;
			}
		}
		return true;

	}

	public static void WriteInfo(data d) {
//		data d = Cumshot.getInfo(turnDir4Inf, dir);
//		dir  = d.dir;
		
		if (d.getCube) {
			real[currCoords[0]][currCoords[1]] = d.cell;
//			if (strCompare(real[currCoords[0]][currCoords[1]], "c")) {
//				cubesHave++;
//			}
		}
		if (d.getWalls) {
			switch (dir) {
			case 'n':
				real[currCoords[0]][(currCoords[1]-1)] = d.walls[0] + "";
				real[currCoords[0]][(currCoords[1]+1)] = d.walls[2] + "";
				real[(currCoords[0]-1)][currCoords[1]] = d.walls[1] + "";
				real[(currCoords[0]+1)][currCoords[1]] = d.walls[3] + "";
				break;
			case 's':
				real[currCoords[0]][(currCoords[1]-1)] = d.walls[2] + "";
				real[currCoords[0]][(currCoords[1]+1)] = d.walls[0] + "";
				real[(currCoords[0]-1)][currCoords[1]] = d.walls[3] + "";
				real[(currCoords[0]+1)][currCoords[1]] = d.walls[1] + "";
				break;
			case 'w':
				real[currCoords[0]][(currCoords[1]-1)] = d.walls[1] + "";
				real[currCoords[0]][(currCoords[1]+1)] = d.walls[3] + "";
				real[(currCoords[0]-1)][currCoords[1]] = d.walls[2] + "";
				real[(currCoords[0]+1)][currCoords[1]] = d.walls[0] + "";
				break;
			case 'e':
				real[currCoords[0]][(currCoords[1]-1)] = d.walls[3] + "";
				real[currCoords[0]][(currCoords[1]+1)] = d.walls[1] + "";
				real[(currCoords[0]-1)][currCoords[1]] = d.walls[0] + "";
				real[(currCoords[0]+1)][currCoords[1]] = d.walls[2] + "";
				break;
			}
		}
		//return dir;
	}
	public static void CngDir(Command cmd, char side) {
		// change dir after rotate, side - side of rotate
		switch (dir) {
		case 'n':
			switch (side) {
			case 'l':
				cmd.dir = 'w';
				break;
			case 'r':
				cmd.dir = 'e';
				break;
			case 'b':
				cmd.dir = 's';
				break;
			}
			break;
		case 's':
			switch (side) {
			case 'l':
				cmd.dir = 'e';
				break;
			case 'r':
				cmd.dir = 'w';
				break;
			case 'b':
				cmd.dir = 'n';
				break;
			}
			break;
		case 'w':
			switch (side) {
			case 'l':
				cmd.dir = 's';
				break;
			case 'r':
				cmd.dir = 'n';
				break;
			case 'b':
				cmd.dir = 'e';
				break;
			}
			break;
		case 'e':
			switch (side) {
			case 'l':
				cmd.dir = 'n';
				break;
			case 'r':
				cmd.dir = 's';
				break;
			case 'b':
				cmd.dir = 'w';
				break;
			}
		}
	}
	public static void stayInBeginDir(char beginDir, char dir) {
		switch (dir) {
		case 'n':
			switch (beginDir) {
			case 'n':
				break;
			case 's':
				Main.turn180();
				break;
			case 'w':
				Main.left90();
				break;
			case 'e':
				Main.right90();
				break;
			}
			break;
		case 's':
			switch (beginDir) {
			case 'n':
				Main.turn180();
				break;
			case 's':
				break;
			case 'w':
				Main.right90();
				break;
			case 'e':
				Main.left90();
				break;
			}
			break;
		case 'w':
			switch (beginDir) {
			case 'n':
				Main.right90();
				break;
			case 's':
				Main.left90();
				break;
			case 'w':
				break;
			case 'e':
				Main.turn180();
				break;
			}
			break;
		case 'e':
			switch (beginDir) {
			case 'n':
				Main.left90();
				break;
			case 's':
				Main.right90();
				break;
			case 'w':
				Main.turn180();
				break;
			case 'e':
				break;
			}
		}
	}
	
	public static void MoveWithThrow(char dir, char cmd) {
		switch (dir) {
		case 'n':
			switch (cmd) {
			case 'w':
				Main.turn180();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 's':
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'a':
				Main.right90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'd':
				Main.left90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			}
			break;
		case 's':
			switch (cmd) {
			case 'w':
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 's':
				Main.turn180();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'a':
				Main.left90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'd':
				Main.right90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			}
			break;
		case 'w':
			switch (cmd) {
			case 'w':
				Main.left90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 's':
				Main.right90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'a':
				Main.turn180();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'd':
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			}
			break;
		case 'e':
			switch (cmd) {
			case 'w':
				Main.right90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 's':
				Main.left90();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'a':
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			case 'd':
				Main.turn180();
				Main.throwCubes();
				Main.back();
				Main.turn180();
				break;
			}
		}

	}
}