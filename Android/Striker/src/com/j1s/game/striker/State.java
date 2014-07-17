package com.j1s.game.striker;

public class State {
	
	private static int gameSizeX;
	private static int gameSizeY;
	public static boolean isAndroyTurn;
	public static boolean isPlayerTurn;
	public static int playerPlaceCoinTime;
	public static int androyPlaceCoinTime;
	public static boolean isStillAcquiringEnemy;
	public static Home homeClass;
	public static int gameCoinHeight;
	public static int gameCoinWidth;
	
	public static int getGameCoinHeight() {
		return gameCoinHeight;
	}

	public static void setGameCoinHeight(int gameCoinHeight) {
		State.gameCoinHeight = gameCoinHeight;
	}

	public static int getGameCoinWidth() {
		return gameCoinWidth;
	}

	public static void setGameCoinWidth(int gameCoinWidth) {
		State.gameCoinWidth = gameCoinWidth;
	}

	public static int getGameSizeX() {
		return gameSizeX;
	}

	public static void setGameSizeX(int gameSizeX) {
		State.gameSizeX = gameSizeX;
	}

	public static int getGameSizeY() {
		return gameSizeY;
	}

	public static void setGameSizeY(int gameSizeY) {
		State.gameSizeY = gameSizeY;
	}

	public static Home getHomeClass() {
		return homeClass;
	}

	public static void setHomeClass(Home homeClass) {
		State.homeClass = homeClass;
	}

	public static boolean isStillAcquiringEnemy() {
		return isStillAcquiringEnemy;
	}

	public static void setStillAcquiringEnemy(boolean isStillAcquiringEnemy) {
		State.isStillAcquiringEnemy = isStillAcquiringEnemy;
	}
	
	public static int getPlayerPlaceCoinTime() {
		return playerPlaceCoinTime;
	}

	public static void setPlayerPlaceCoinTime(int playerPlaceCoinTime) {
		State.playerPlaceCoinTime = playerPlaceCoinTime;
	}

	public static int getAndroyPlaceCoinTime() {
		return androyPlaceCoinTime;
	}

	public static void setAndroyPlaceCoinTime(int androyPlaceCoinTime) {
		State.androyPlaceCoinTime = androyPlaceCoinTime;
	}

	public static boolean isPlayerTurn() {
		return isPlayerTurn;
	}

	public static void setPlayerTurn(boolean isPlayerTurn) {
		State.isPlayerTurn = isPlayerTurn;
	}

	public static boolean isAndroyTurn() {
		return isAndroyTurn;
	}

	public static void setAndroyTurn(boolean isAndroyTurn) {
		State.isAndroyTurn = isAndroyTurn;
	}

}
