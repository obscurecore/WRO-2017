
public class Finder {

	static int up = 0;
	static int down = 0;
	static int left = 0;
	static int right = 0;
	
	static int memI = 0;
	static int memJ = 0;
//	static int memI2 = 0;
//	static int memJ2 = 0;
//	static int memI3 = 0;
//	static int memJ3 = 0;

	
	public static int Matches(Command cmd) {
		//char dir, boolean compass, String[][] map, String[][] comp
		// dir - direction of move
		/*	String[][] map = {{"b","0","c","0","e","0","b","1","b","0","c","0","c","0","b"},{"0",null,"1",null,"0",null,"0",null,"0",null,"1",null,"1",null,"0"},
		  {"c","0","d","1","c","1","d","1","c","1","x","1","b","0","b"},{"0",null,"1",null,"0",null,"1",null,"0",null,"1",null,"0",null,"1"},
		  {"c","1","x","1","e","0","c","0","e","0","e","0","e","1","d"},{"0",null,"1",null,"0",null,"1",null,"1",null,"0",null,"0",null,"0"},
		  {"b","0","b","0","e","0","c","0","c","0","b","1","b","0","b"}};
*/
	//	String [][] map = {{"1","0","0","0","1","0","1"},{"0","1","1","1","0","1","0"},{"1","0","1","1","0","0","0"},{"0","0","0","0","1","1","1"},{"1","1","1","0","1","0","0"}};
	//	String [][] comp = {{"1",null,null,"1"},{null,null,"0",null}};
	//	Command cmd = new Command();
		int q = 0;
		String[][] comp = Cutarr(cmd.real);
		
		int y = cmd.map.length;
		int x = cmd.map[0].length; // const for any i
		//
		for (int i = 0, comy = comp.length; i < (y - comy + 1); i++) {
			for (int j = 0, comx = comp[0].length; j < (x - comx + 1); j++) {
				if (comp[0][0] == null || cmd.strCompare(cmd.map[i][j], comp[0][0]) || ((cmd.strCompare(cmd.map[i][j], "x")) && (cmd.strCompare(comp[0][0], "a")))) {
		//		if (comp[0][0] == null || cmd.strCompare(map[i][j], comp[0][0])) {
					int i2 = i;
					int j2 = j;
					For:for (int i3 = 0; i3 < comy; i3++) {
						for (int j3 = 0; j3 < comx; j3++) {		
							if (comp[i3][j3] != null  && !cmd.strCompare(comp[i3][j3], cmd.map[i2][j2]) && !(((cmd.strCompare(cmd.map[i][j], "x")) && (cmd.strCompare(comp[0][0], "a"))))) { //here maybe a condition for don't researched field
					//		if (comp[i3][j3] != null  && !cmd.strCompare(comp[i3][j3], map[i2][j2])) {
								break For;
							}
							if (i3 == (comy - 1) && j3 == (comx - 1)) {
								q++;
							}
							if (q > 1){
								return q;
							}
							j2++;
						}
						i2++;
						j2 = j;
					}
				}
			}
		}
		if (q == 0) {
			System.out.println("Total error in Search module, find compares. Matches found: 0");
		}
		return q;

	}

	public static void getCoordinats(Command cmd) {
	//	int[] beginCoords, char dir, boolean compass, String[][] map, String[][] comp
		//Command cmd = new Command();
		int y = cmd.map.length;
		int x = cmd.map[0].length; // const for any i
		//
		int[] coords = new int[4];

			
		for (int i = 0, comy = cmd.real.length; i < (comy - y + 1); i++) {
			for (int j = 0, comx = cmd.real[i].length; j < (comx - x + 1); j++) {
				if (cmd.strCompare(cmd.real[i][j], cmd.map[0][0]) || cmd.real[0][0] == null) { //|| ((cmd.strCompare(cmd.map[0][0], "x")) && (cmd.strCompare(cmd.real[i][j], "a")))) {
					int i2 = i;
					int j2 = j;
					For:for (int i3 = 0; i3 < y; i3++) {
							for (int j3 = 0; j3 < x; j3++) {
								if (!cmd.strCompare(cmd.map[i3][j3], cmd.real[i2][j2]) && cmd.real[i2][j2] != null) { //&& !((cmd.strCompare(cmd.map[i3][j3], "x")) && (cmd.strCompare(cmd.real[i2][j2], "a")))) { //here maybe a condition for don't researched field
									break For;
								}
								if (i3 == (y - 1) && j3 == (x - 1)) {
									//its a coords in full map in the begin_last cell
									cmd.currCoords[2] = (cmd.Y_EXT-2 - i);
									cmd.currCoords[3] = (cmd.X_EXT-2 - j);
									//its a coords in full map in now momen
									cmd.currCoords[0] = (cmd.currCoords[0] - i);
									cmd.currCoords[1] = (cmd.currCoords[1] - j);
									memI = i;
									memJ = j;		
									return;
								}
								j2++;
							}
						i2++;
						j2 = j;
					}
				}
			}

		}
		
		
		
//		String[][] comp = Cutarr(cmd.real);		
//		for (int i = 0, comy = comp.length; i < (y - comy + 1); i++) {
//			for (int j = 0, comx = comp[0].length; j < (x - comx + 1); j++) {
//				if (cmd.strCompare(cmd.map[i][j], comp[0][0]) || comp[0][0] == null) {
//					int i2 = i;
//					int j2 = j;
//					For:for (int i3 = 0; i3 < comy; i3++) {
//						for (int j3 = 0; j3 < comx; j3++){
//							if (!cmd.strCompare(comp[i3][j3], cmd.map[i2][j2]) && comp[i3][j3] != null) { //here maybe a condition for don't researched field
//								break For;
//							}
//							if (i3 == (comy - 1) && j3 == (comx - 1)) {
//								//its a coords in full map in the begin_last cell
//								cmd.currCoords[2] = (i + cmd.Y_EXT-2-up);
//								cmd.currCoords[3] = (j + cmd.X_EXT-2-left);
//								//its a coords in full map in now momen
//								cmd.currCoords[0] = (i + cmd.currCoords[0] - up);
//								cmd.currCoords[1] = (j + cmd.currCoords[1] - left); 
//								
//								memI = i;
//								memJ= j;
//								memI2 = i2;
//								memJ2= j2;
//								memI3 = i3;
//								memJ3= j3;
//								
//								return;
//							}
//							j2++;
//						}
//						i2++;
//						j2 = j;
//					}
//				}
//			}
//
//		}

	}
	public static String[][] Cutarr(String[][] realmap) {

		//int[] hordel = new int[realmap.length*realmap[0].length]; // it is numbers of lines which need delete
	//	int[] verdel = new int[realmap.length*realmap[0].length];
		int realy = realmap.length;
		int realx = realmap[0].length;
		
		int numup = 0;
		int numdown = 0;
	up:for (int i = 0; i < realy; i++) {
			for (int j = 0; j < realx; j++) {
				if (realmap[i][j] != null) break up;
				if (j == (realx-1)) {
				//	hordel[num] = i;
					numup++;		
				}
			}
		}
   down:for (int i = (realy-1); i > 0; i--) {
			for (int j = 0; j < realx; j++) {
				if (realmap[i][j] != null) break down;
				if (j == (realx-1)) {
				//	hordel[num] = i;
					numdown++;		
				}
			}
		}
		
		int numleft = 0;
		int numright = 0;
	left:for (int i = 0; i < realx; i++) {
			for (int j = 0; j < realy; j++) {
				if (realmap[j][i] != null) break left;
				if (j == (realy-1)) {
				//	verdel[numleft] = i;
					numleft++;
				}
			}
		}
  right:for (int i = (realx - 1); i > 0; i--) {
			for (int j = 0; j < realy; j++) {
				if (realmap[j][i] != null) break right;
				if (j == (realy-1)) {
				//	verdel[numleft] = i;
					numright++;
				}
			}
		}
	up = numup;
	down = numdown;
	left = numleft;
	right = numright;
	String[][] newarr = new String[realy-numup-numdown][realx-numleft-numright];


	int i2 = 0;
	int j2 = 0;
	for (int i = numup, real2y = realy - numdown; i < real2y; i++) {
		for (int j = numleft, real2x = realx - numright; j < real2x; j++) {
			newarr[i2][j2] = realmap[i][j];
			j2++;
		}
		i2++;
		j2 = 0;
	}
	return newarr;
		
	}
//	public static boolean isNormal(String[][] real, int[] sideWalls, int[] Coords, char dir) {
//		// side walls[0] - left sensor, [1] - right
//		switch (dir) {
//		case 'n':
//			if (!Command.strCompare(real[Coords[0]][(Coords[1]-1)], (sideWalls[0] + "")) && !Command.strCompare(real[Coords[0]][(Coords[1]+1)], (sideWalls[1] + ""))) {
//				System.out.println("Wrong direction, open isNormal func.....");
//				return false;
//			}
//		case 's':
//			if (!Command.strCompare(real[Coords[0]][(Coords[1]-1)], (sideWalls[1] + "")) && !Command.strCompare(real[Coords[0]][(Coords[1]+1)], (sideWalls[0] + ""))) {
//				System.out.println("Wrong direction, open isNormal func.....");
//				return false;
//			}
//		case 'w':
//			if (!Command.strCompare(real[(Coords[0]-1)][(Coords[1])], (sideWalls[1] + "")) && !Command.strCompare(real[(Coords[0]+1)][(Coords[1])], (sideWalls[0] + ""))) {
//				System.out.println("Wrong direction, open isNormal func.....");
//				return false;
//			}
//		case 'e':
//			if (!Command.strCompare(real[(Coords[0]-1)][(Coords[1])], (sideWalls[1] + "")) && !Command.strCompare(real[(Coords[0]+1)][(Coords[1])], (sideWalls[0] + ""))) {
//				System.out.println("Wrong direction, open isNormal func.....");
//				return false;
//			}
//		}
//		System.out.println("Error in the isNormal");
//		return false;
//	}

	
}
