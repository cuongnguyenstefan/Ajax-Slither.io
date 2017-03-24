package edu.mum.cs472.slitherio.constant;

public class GameConfig {

	private static GameConfig gameConfig = null;

	public static GameConfig getInstance() {
		if (gameConfig == null) {
			gameConfig = new GameConfig();
		}
		return gameConfig;
	}

	private GameConfig() {
	}

	public final int initSnakeX = 0;

	public final int initSnakeY = 0;

	public final int boundaryXMin = 0;

	public final int cellPx = 10;

	public final int gameCellPerHeight = 80;

	public final int gameCellPerWidth = 120;

	public final int boundaryXMax = gameCellPerWidth * cellPx + cellPx;

	public final int boundaryYMin = 0;

	public final int boundaryYMax = gameCellPerHeight * cellPx + cellPx;

	public final int gameSpeed = 80;

	public final int foodThresholdPerPlayer = 10;

	public final int foodCreateNumber = 3;

	public int getGameSpeed() {
		return gameSpeed;
	}

	public int getCellPx() {
		return cellPx;
	}

	public int getGameCellPerHeight() {
		return gameCellPerHeight;
	}

	public int getGameCellPerWidth() {
		return gameCellPerWidth;
	}

	public int getFoodThresholdPerPlayer() {
		return foodThresholdPerPlayer;
	}

	public int getFoodCreateNumber() {
		return foodCreateNumber;
	}

	public int getBoundaryXMin() {
		return boundaryXMin;
	}

	public int getBoundaryXMax() {
		return boundaryXMax;
	}

	public int getBoundaryYMin() {
		return boundaryYMin;
	}

	public int getBoundaryYMax() {
		return boundaryYMax;
	}

	public int getInitSnakeX() {
		return initSnakeX;
	}

	public int getInitSnakeY() {
		return initSnakeY;
	}
}
