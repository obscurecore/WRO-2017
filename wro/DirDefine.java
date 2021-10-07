
public class DirDefine {
		
		public static char def(int NORTH, int deg) {
			
			if (cons(NORTH, deg)) {
				return 'n';
			}
			int EAST = val(NORTH);
			int SOUTH = val(EAST);
			int WEST = val(SOUTH);
			if (cons(WEST, deg)) {
				return 'w';
			}
			if (cons(SOUTH, deg)) {
				return 's';
			}
			if (cons(EAST, deg)) {
				return 'e';
			}	
			return '0'; 
		}
		public static boolean cons(int side, int deg) {
			if (deg >= side) {
				if ((deg - side) <= 44 || (360 - deg + side) <= 45) {
					return true;
				}
			}
			else {
				if ((side - deg) <= 45 || (360 - side + deg) <= 44) {
					return true;
				}
			}
			return false;
		}
		public static int val(int prev) {
			// prev - value of previous direction 
			if (prev < 270) {
				return (prev + 90);
			}
			else {
				return (90 - (360 - prev));
			}
		}
}
