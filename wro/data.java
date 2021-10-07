
public class data {


//	static boolean isCube;
//	static char dir;
	static int[] walls;
	static String cell;

	static boolean getCube = false;  // this variables show, do we read isCube and walls[], for 1st move and moving after localization
	static boolean getWalls = false;
	
	data(int[] walls) {
		this.walls = walls;
		getWalls = true;
	}
	data(boolean isSubj) {
		getCube = true;
		if (isSubj) {
			cell = "c";
		}
		else {
			cell = "a";
		}
	}
	data(boolean isSubj, int[] walls) {
		this.walls = walls;
		getWalls = true;
		getCube = true;
		if (isSubj) {
			cell = "c";
		}
		else {
			cell = "a";
		}
	}
//	data(int[] walls, boolean isSubj, char direct) {
//		this.walls = walls;
//		getWalls = true;
//		isCube = isSubj;
//		getCube = true;
//		if (isCube) {
//			cell = "c";
//		}
//		else {
//			cell = "a";
//		}
//		dir = direct;
//	}
//	public int[] getWalls() {
//		return walls;
//	}
//	public boolean getCube() {
//		return isCube;
//	}
//	public char getDir() {
//		return dir;
//	}
}
