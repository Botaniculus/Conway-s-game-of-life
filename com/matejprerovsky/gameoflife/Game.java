package com.matejprerovsky.gameoflife;

import java.util.Random;

public class Game {
	// make it GUI
	private final static int VERTICAL = 7;
	private final static int HORIZONTAL = 26;
	
	private final static char LIVE_CELL = '#';
	private final static char DEAD_CELL = '.';
	
	private static char[][] canvas;
	public static void main(String[] args) {
		canvas = new char[VERTICAL][HORIZONTAL];
		randomizeCanvas();
		/*
		canvas[3][3] = LIVE_CELL;
		canvas[3][4] = LIVE_CELL;
		
		canvas[4][4] = LIVE_CELL;
		canvas[4][5] = LIVE_CELL;
		
		canvas[5][5] = LIVE_CELL;
		canvas[5][6] = LIVE_CELL;
		
		
		canvas[6][6] = LIVE_CELL;
		canvas[6][7] = LIVE_CELL;
		*/
		
		System.out.println("Generation 0");
		System.out.println(printCanvas());
		String old="";
		int generation = 1;
		while(!old.equals(printCanvas())) {
			old = printCanvas();
			nextGeneration();
			if(!old.equals(printCanvas())) {
				System.out.println("Generation "+generation);
				generation++;
				System.out.println(printCanvas());
			}
		}
		
	}
	
	public static void randomizeCanvas() {
		Random random = new Random();
		for(int i=0; i<VERTICAL; i++) {
			for(int j=0; j<HORIZONTAL; j++) {
				int r = random.nextInt(4);
				canvas[i][j] = (r==0) ? LIVE_CELL : DEAD_CELL;
			}
		}
	}
	
	public static void clearCanvas() {
		for(int i=0; i<VERTICAL; i++) {
			for(int j=0; j<HORIZONTAL; j++) {
				canvas[i][j] = DEAD_CELL;
			}
		}
	}
	public static String printCanvas() {
		String outputString = "";
		for(int i=0; i<VERTICAL; i++) {
			for(int j=0; j<HORIZONTAL; j++) {
				outputString+=canvas[i][j];
			}
			outputString+="\n";
		}
		return outputString;
	}
	
	public static int neighborsCount(int x, int y) {
		int count = 0;
		for(int i=y-1; i<=y+1; i++) {
			for(int j=x-1; j<=x+1; j++) {
				if(i==y && j==x) continue;
				try {
					count+=(canvas[i][j] == LIVE_CELL) ? 1 : 0;
				} catch (ArrayIndexOutOfBoundsException e) {}
			}
		}
		return count;
	}
	
	public static void nextGeneration() {
		char[][] copy = new char[VERTICAL][HORIZONTAL];
 		for(int i=0; i<VERTICAL; i++) {
			for(int j=0; j<HORIZONTAL; j++) {
				int neighbors = neighborsCount(j, i);
				if(canvas[i][j] == DEAD_CELL) {
					copy[i][j] = (neighbors==3) ? LIVE_CELL : DEAD_CELL;
				} else{
					copy[i][j] = ((neighbors<2) || (neighbors>3)) ? DEAD_CELL : LIVE_CELL;
				}
			}
		}
 		canvas=copy.clone();
	}

}
