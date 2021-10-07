 import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Robot;
import javax.swing.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.*;
import java.applet.*;
import java.lang.*;
import java.awt.event.*;

/*
	b0c0e0b1b0c0c0b
	0 1 0 0 0 1 1 0
	c0d1c1d1c1x1b0b
	0 1 0 1 0 1 0 1
	c1x1e0c0e0e0e1d
	0 1 0 1 1 0 0 0
	b0b0e0c0c0b1b0b

*/

public class MinDistance {

	public static char[] Minmain(String[][] arr, int sty, int stx,int finy, int finx) {
		// x and y - robot cells on x and y

	//	System.out.println("ARR");
//	for (int i = 0; i < arr.length; i++) {
//		for (int j = 0; j < arr[i].length; j++) {
//			if (arr[i][j] != null) {
//				System.out.print(arr[i][j]);
//			}
//			else {
//				System.out.print(" ");
//			}
//		}
//		System.out.println();
//	}
		
	if (sty == finy && stx == finx) {
		// Dont do anything, we already stand at necessary cell
		char[] n = {'n'};
		return n;
	}
		
	int y = (arr.length-1)/2;	// count robot cells in arr 
	int x = (arr[0].length-1)/2;

	
	int[][] road = new int[x*y][x*y];
	
	for (int i = 1; i <= (arr.length-2); i+=2) {
		for (int j = 1; j <= (arr[i].length-2); j+=2) {
			if (i >= 3) {
				if (Command.strCompare(arr[i-1][j], "0")) {
					road[(i-1)/2*x+(j-1)/2][((i-1)/2-1)*x+(j-1)/2] = 1;
				}
				else {
					road[(i-1)/2*x+(j-1)/2][((i-1)/2-1)*x+(j-1)/2] = 0;
				}
			}
			if (i <= (arr.length-4)) {
				if (Command.strCompare(arr[i+1][j], "0")) {
					road[(i-1)/2*x+(j-1)/2][((i-1)/2+1)*x+(j-1)/2] = 1;
				}
				else {
					road[(i-1)/2*x+(j-1)/2][((i-1)/2+1)*x+(j-1)/2] = 0;
				}	
			}
			if (j >= 3) {
				if (Command.strCompare(arr[i][j-1], "0")) {
					road[(i-1)/2*x+(j-1)/2][(i-1)/2*x+(j-1)/2-1] = 1;
				}
				else {
					road[(i-1)/2*x+(j-1)/2][(i-1)/2*x+(j-1)/2-1] = 0;
				}
			}
			if (j <= (arr[i].length-4)) {
				if (Command.strCompare(arr[i][j+1], "0")) {
					road[(i-1)/2*x+(j-1)/2][(i-1)/2*x+(j-1)/2+1] = 1;
				}
				else {
					road[(i-1)/2*x+(j-1)/2][(i-1)/2*x+(j-1)/2+1] = 0;
				}
			}
		}
	}
	
//	for (int i = 0; i < y; i++) {
//		for (int j = 0; j < x; j++) {
//			//check neighborhood matrix arr[i*2+1][j*2+1]
//			/*
//			if (i == 0 && j == 0) {
//				if (arr[i*])
//			}
//			*/
//			if ((i-1) >= 0) {
//				if (Command.strCompare(arr[i*2][j*2+1], "0")) {
//					road[i*x+j][(i-1)*x+j] = 1;
//				}
//				else {
//					road[i*x+j][(i-1)*x+j] = 0;
//				}
//			}
//			if ((i+1) < y) {
//				if (Command.strCompare(arr[i*2+2][j*2+1], "0")) {
//					road[i*x+j][(i+1)*x+j] = 1;
//				}
//				else {
//					road[i*x+j][(i+1)*x+j] = 0;
//				}	
//			}
//			if ((j-1) >= 0) {
//				if (Command.strCompare(arr[i*2+1][j*2], "0")) {
//					road[i*x+j][i*x+j-1] = 1;
//				}
//				else {
//					road[i*x+j][i*x+j-1] = 0;
//				}
//			}
//			if ((j+1) < x) {
//				if (Command.strCompare(arr[i*2+1][j*2+2], "0")) {
//					road[i*x+j][i*x+j+1] = 1;
//				}
//				else {
//					road[i*x+j][i*x+j+1] = 0;
//				}
//			}
//			}	
//	} 	

//	System.out.print("  ");
//	for (int i = 0; i < road.length; i++) {
//		if (i < 10) {
//			System.out.print("  " + i);
//		}
//		else {
//			System.out.print(" " + i);
//		}
//		
//	}
//	System.out.println();
//	for (int i = 0; i < road.length; i++) {
//		if (i < 10) {
//			System.out.print(" "+ i);
//		}
//		else {
//			System.out.print(i);
//		}
//		for (int j = 0; j < road[i].length; j++) {
//			System.out.print("  " + road[i][j]);
//		}
//		System.out.println();
//	}
	
	
	//import coordinats in matrix road
	int start = ((sty-1)/2)*x + (stx-1)/2;
	int finish = ((finy-1)/2)*x + (finx-1)/2;

	int lim = 30000;
	
	int rl = road.length;
	int[][] weight = new int[rl][rl];
	for (int i = 0; i < rl; i++) {
		for (int j = 0; j < rl; j++) {
			if (road[i][j] == 1) {
				weight[i][j] = 1;
			}
			else {
				weight[i][j] = lim;
			}
		}
	}
	
	//i can use 2 new arrays or create object cell with parametrs dist and from
	int[] dist = new int[rl]; //distance before each cell from current cell
	for (int i = 0; i < rl; i++) {
		dist[i] = lim;
	}
	dist[start] = 0;
	
	int[] from = new int[rl];
	
	//it show was i in this node or not
	boolean[] was = new boolean[rl];
	for (int i = 0; i < rl; i++) {
		was[i] = false;
	}

	while (true) {
			
		int curnode = MinVal(was, dist);
		was[curnode] = true;
		
		if (curnode == finish) break;
		for (int i = 0; i < rl; i++) {
			if (road[curnode][i] == 1) {
				if (dist[i] > dist[curnode] + weight[curnode][i]) {
					dist[i] = dist[curnode] + weight[curnode][i];
					from[i] = curnode;
				}
			}
		}
		
	}
	//can do int[dist[finish]-1] without start and finish
	int[] path = new int[dist[finish]+1];
	int dp = finish;
	for (int i = dist[finish]; i >= 0; i--) {
		path[i] = dp;
		dp = from[dp];
	}
	
	char[] result = new char[path.length - 1];
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	for (int i = 0; i < (path.length - 1); i++) {
		x1 = (path[i] % x)*2 + 1;
		y1 = (path[i] - (path[i] % x))/x*2 + 1;
		x2 = (path[i+1] % x)*2 + 1;
		y2 = (path[i+1] - (path[i+1] % x))/x*2 + 1;
		result[i] = WhatDo(x1,y1,x2,y2);
		
	}
	return result;
	
	}
	public static char WhatDo(int x1, int y1, int x2, int y2) {
		if (y1 == y2) {
			if (x1 > x2) {
//				System.out.println("Left!");
				return 'a';
			}
			else {
//				System.out.println("Right!");
				return 'd';
			}
		}
		else if (x1 == x2) {
			if (y1 > y2) {
//				System.out.println("Up!");
				return 'w';
			}
			else {
//				System.out.println("Down!");
				return 's';
			}
		}
		else {
			System.out.println("Fatal Error");
			return '0';
		}
	}
	public static int MinVal(boolean[] was, int[] dist) {
		int min = 30000;
		int minindx = 0;
		for (int i = 0; i < dist.length; i++) {
			if (!was[i] && dist[i] < min) {
				min = dist[i];
				minindx = i;
			}
		}
		return minindx;
	}

}

