
public class getTarget {
//	static Command cmd = new Command();
	static int cubeOnMap = Command.ALL_CUBES - Command.cubesHave;
	static int[][] cubes = new int[cubeOnMap][2];
	static int[] end = new int[2];
	static int count = 0;
	
	public static void getCubeCoords(String[][] arr) {
		for (int i = 1, li = (arr.length-2); i <= li; i+=2) {
			for (int j = 1, lj = (arr[i].length-1); j <= lj; j+=2) {
				if (Command.strCompare(arr[i][j], "c") && !Command.strCompare(Command.real[i + Finder.memI][j + Finder.memJ], "c")) {
					cubes[count][0] = i;
					cubes[count][1] = j;
					count++;
				}
//				if (Command.strCompare(arr[i][j], "c") && !Command.strCompare(Command.real[i+Finder.up - Finder.memI][j+Finder.left - Finder.memJ], "c")) {
//					cubes[count][0] = i;
//					cubes[count][1] = j;
//					count++;
//				}
			}
		}
	}
//	public static void getWareHouse(String[][] arr) {
//		for (int i = 1, li = (arr.length-2); i <= li; i+=2) {
//			for (int j = 1, lj = (arr[i].length-2); j <= lj; j+=2) {
//				if (Command.strCompare(arr[i][j], "x")) {
//					end[0] = i;
//					end[1] = j;
//					arr[i][j] = "a";
//					return;
//				}
//			}
//		}	
//	}
	public static char[][] setTarget(String[][] arr, int[] currCoords) {
		// we return intervals ways
		// 0 - from current position to 1st cube(or to warehouse if (cubesCount == ALL_CUBES))
		// 1 from 1st cube to 2nd cube(or to warehouse if (cubesCount == ALL_CUBES))
		// 2 from 2nd cube to 3rd cub(or to warehouse if (cubesCount == ALL_CUBES))
		// 3 from 3rd cube to warehouse, (cubesCount == ALL_CUBES)
		
		getCubeCoords(arr);
//		getWareHouse(arr);
		
		char[][] way = new char[cubeOnMap+1][];
		
		if (cubeOnMap == 0) {
			way[0] = MinDistance.Minmain(arr, currCoords[0], currCoords[1], end[0], end[1]);
		}
		if (cubeOnMap == 1) {
			way[0] = MinDistance.Minmain(arr, currCoords[0], currCoords[1], cubes[0][0], cubes[0][1]);
			way[1] = MinDistance.Minmain(arr, cubes[0][0], cubes[0][1], end[0], end[1]);
		}
		if (cubeOnMap == 2) {
			int len = 0;
			int minlen = 0;
			int[] indexMinlen = new int[cubeOnMap+1];
			for (int i = 0; i < cubeOnMap; i++) {
				len += MinDistance.Minmain(arr, currCoords[0], currCoords[1], cubes[i][0], cubes[i][1]).length;
				for (int j = 0; j < cubeOnMap; j++) {
					if (i == j) continue;
					len += MinDistance.Minmain(arr, cubes[i][0], cubes[i][1], cubes[j][0], cubes[j][1]).length;
					if (i == 0 && j == 1) {
						minlen = len;
						indexMinlen[0] = i;
						indexMinlen[1] = j;
					}
					else if (minlen > len) {
						minlen = len;
						indexMinlen[0] = i;
						indexMinlen[1] = j;
					}
				}
			}
			way[0] = MinDistance.Minmain(arr, currCoords[0], currCoords[1], cubes[indexMinlen[0]][0], cubes[indexMinlen[0]][1]);
			way[1] = MinDistance.Minmain(arr, cubes[indexMinlen[0]][0], cubes[indexMinlen[0]][1], cubes[indexMinlen[1]][0], cubes[indexMinlen[1]][1]);
			way[2] = MinDistance.Minmain(arr, cubes[indexMinlen[1]][0], cubes[indexMinlen[1]][1], end[0], end[1]);
		}
		if (cubeOnMap == 3) {
			int len = 0;
			int minlen = 0;
			int[] indexMinlen = new int[cubeOnMap+1];
			for (int i = 0; i < cubeOnMap; i++) {
				len = 0;
				len += MinDistance.Minmain(arr, currCoords[0], currCoords[1], cubes[i][0], cubes[i][1]).length;
				for (int j = 0; j < cubeOnMap; j++) {
					if (i == j) continue;
					len += MinDistance.Minmain(arr, cubes[i][0], cubes[i][1], cubes[j][0], cubes[j][1]).length;
					for (int k = 0; k < cubeOnMap; k++) {
						if (k == i || k == j) continue;
						if (i == 0 && j == 1) {
							minlen = len;
							indexMinlen[0] = i;
							indexMinlen[1] = j;
							indexMinlen[2] = k;
						}
						else if (minlen > len){
							minlen = len;
							indexMinlen[0] = i;
							indexMinlen[1] = j;
							indexMinlen[2] = k;
						}
					}
				}
			}
			way[0] = MinDistance.Minmain(arr, currCoords[0], currCoords[1], cubes[indexMinlen[0]][0], cubes[indexMinlen[0]][1]);
			for (int i = 0; i < (cubeOnMap-1); i++) {
				way[i+1] = MinDistance.Minmain(arr, cubes[indexMinlen[i]][0], cubes[indexMinlen[i]][1], cubes[indexMinlen[i+1]][0], cubes[indexMinlen[i+1]][1]);
			}
			way[cubeOnMap] = MinDistance.Minmain(arr, cubes[indexMinlen[cubeOnMap-1]][0], cubes[indexMinlen[cubeOnMap-1]][1], end[0], end[1]);
		}
		
		return way;
		
	}
}
