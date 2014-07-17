package com.j1s.game.striker;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;

public class AndroyMove {
	
	private int GAME_SIZE_X;
	private int GAME_SIZE_Y;
	private PlayingField playingField;
	private int maxPlayerCoins = 0;
	private int maxJumpPosPlayerCoins = 0;
	private ArrayList<Integer> maxPlayerCoinsPos = new ArrayList<Integer>();
	private  ArrayList<Integer> maxJumpToPlayerCoinsPos = new ArrayList<Integer>();
	private boolean placeNewCoin;
	private int placeCoinTime;
	
	public AndroyMove(){
		this.GAME_SIZE_X = State.getGameSizeX();
		this.GAME_SIZE_Y = State.getGameSizeY();
	}
	
	@SuppressWarnings("unchecked")
	public int placeCoin(final Context ctx){
		int neighbourPlayerCoinCount = 0;
		int neighbourAndroyCoinCount = 0;
		ArrayList<Integer> possibleJumpFromPositions = new ArrayList<Integer>();
		int canPutFlag = 0;
		int canJumpFlag = 0;
		boolean enteredEmpty = false;
		boolean coinInHand   = false;
		boolean haveJumpPos  = false;
		
		maxPlayerCoins = 0;
		maxJumpPosPlayerCoins = 0;
		maxPlayerCoinsPos.clear();
		maxJumpToPlayerCoinsPos.clear();
		
		for(int i=0; i<GAME_SIZE_X; i++) {
    		for(int j=0; j<GAME_SIZE_Y; j++) {
    			//To find the position to plot the coin
    			if(Game.coinBucket[i][j].getState() == CoinBucket.EMPTY__COIN_STATE) {
    				enteredEmpty = true;
    				neighbourPlayerCoinCount=0;
    				for(int x=i-1; x<=i+1; x++) {
    		    		for(int y=j-1; y<=j+1; y++) {
    		    			if((x>=0 && x<GAME_SIZE_X) && (y>=0 && y<GAME_SIZE_Y)) {
    		    				if(!(x==i && y==j)){
	    		    				if(Game.coinBucket[x][y].getState() == CoinBucket.ANDROY_COIN_STATE){
	    		    					canPutFlag = 1;
	    		    					coinInHand = true;
	    		    				}
	    		    				if(Game.coinBucket[x][y].getState() == CoinBucket.PLAYER_COIN_STATE)
	    		    					neighbourPlayerCoinCount++;
    		    				}
    		    			}
    		    		}
    		    	}
    				if(canPutFlag == 1) {// Adding New Androy coin
    					if(maxPlayerCoins < neighbourPlayerCoinCount) {
    						maxPlayerCoins = neighbourPlayerCoinCount;
    					}
    					Game.coinBucket[i][j].setPlayerCoinCount(neighbourPlayerCoinCount);
    					Game.coinBucket[i][j].setJumpPosPlayerCoinCount(-1);
    				}else {// Jumping existing Androy Coin

    					Game.coinBucket[i][j].setPlayerCoinCount(-1);
    					for(int x=i-2; x<=i+2; x+=2){
    						for(int y=j-2; y<=j+2; y+=2){
    							if((x>=0 && x<GAME_SIZE_X) && (y>=0 && y<GAME_SIZE_Y)) {
    								if(!(x==i && y==j)){
	    								if(Game.coinBucket[x][y].getState() == CoinBucket.ANDROY_COIN_STATE){
	    									canJumpFlag = 1;
	    									haveJumpPos = true;
	    									possibleJumpFromPositions.add((x*10)+y);
	    								}
    								}
    							}
    						}
    					}
    					
    					if(canJumpFlag == 1) {
        					if(maxJumpPosPlayerCoins < neighbourPlayerCoinCount)
            					maxJumpPosPlayerCoins = neighbourPlayerCoinCount;
    						Game.coinBucket[i][j].setJumpPosPlayerCoinCount(neighbourPlayerCoinCount);
							Game.coinBucket[i][j].setPossibleJumpFromPositions((ArrayList<Integer>) possibleJumpFromPositions.clone());
    						possibleJumpFromPositions.clear();
    					}else {
    						Game.coinBucket[i][j].setJumpPosPlayerCoinCount(-1);
    					}
    						
    							
    				}
    				canJumpFlag = 0;
    				canPutFlag = 0;
    				neighbourPlayerCoinCount = 0;
    			}//To find the Jump positions
    			else if(Game.coinBucket[i][j].getState() == CoinBucket.ANDROY_COIN_STATE) { 
    				neighbourAndroyCoinCount = 0;
					for(int x=i-1; x<=i+1; x++) {
						for(int y=j-1; y<=j+1; y++) {
							if(!(x==i && y==j)){
								if((x>=0 && x<GAME_SIZE_X) && (y>=0 && y<GAME_SIZE_Y)) {
									if(Game.coinBucket[x][y].getState() == CoinBucket.ANDROY_COIN_STATE){
										neighbourAndroyCoinCount++;
									}
								}
							}
						}
					}
					Game.coinBucket[i][j].setNeighbourAndroyCoinCount(neighbourAndroyCoinCount);
					Game.coinBucket[i][j].setPlayerCoinCount(-1);
    				Game.coinBucket[i][j].setJumpPosPlayerCoinCount(-1);
					neighbourAndroyCoinCount = 0;
    			}else { // If Player Coin positions
					Game.coinBucket[i][j].setPlayerCoinCount(-1);
    				Game.coinBucket[i][j].setJumpPosPlayerCoinCount(-1);
    				Game.coinBucket[i][j].setNeighbourAndroyCoinCount(-1);
    			}
			}
    	}
		if(!enteredEmpty){
			return 500;
		}else if(!coinInHand && !haveJumpPos){
			return -1;
		}

		for(int m=0; m<GAME_SIZE_X; m++) {
    		for(int n=0; n<GAME_SIZE_Y; n++) {
    			if(maxPlayerCoins+2 > maxJumpPosPlayerCoins) { // To set New Coin
	    			if(maxPlayerCoins == Game.coinBucket[m][n].getPlayerCoinCount()) {
	    				maxPlayerCoinsPos.add((m*10)+n);
	    			}
	    			placeNewCoin = true;
    			}else { // To Jump existing coin
    				if(maxJumpPosPlayerCoins == Game.coinBucket[m][n].getJumpPosPlayerCoinCount()) {
	    				maxJumpToPlayerCoinsPos.add((m*10)+n);
	    			}
    				placeNewCoin = false;
    			}
    			
    		}
		}	
		
		if(placeNewCoin){//Placing new Androy coin
			Random rand = new Random();
			int randNo = rand.nextInt(maxPlayerCoinsPos.size());
			int newPos = maxPlayerCoinsPos.get(randNo);
			maxPlayerCoinsPos.clear();
			int ii = newPos/10;
			int jj = newPos - (ii*10);
			playingField = new PlayingField(ctx, Game.coinBucket[ii][jj], null);
			return playingField.placeCoin(CoinBucket.ANDROY_COIN_STATE);
			 
		}else{// jumping existing Adroy coin
			Random rand = new Random();
			int jumpToRandNo = rand.nextInt(maxJumpToPlayerCoinsPos.size());
			int jumpToPos = maxJumpToPlayerCoinsPos.get(jumpToRandNo);
			maxJumpToPlayerCoinsPos.clear();
			int iJumpTo = jumpToPos/10;
			int jJumpTo = jumpToPos - (iJumpTo*10);
			ArrayList<Integer> maxJumpFromPlayerCoinsPos = Game.coinBucket[iJumpTo][jJumpTo].getPossibleJumpFromPositions();
			int jumpFromRandNo = rand.nextInt(maxJumpFromPlayerCoinsPos.size());
			int jumpFromPos = maxJumpFromPlayerCoinsPos.get(jumpFromRandNo);
			maxJumpFromPlayerCoinsPos.clear();
			Game.coinBucket[iJumpTo][jJumpTo].clearPossibleJumpFromPositions();
			int iJumpFrom = jumpFromPos/10;
			int jJumpFrom = jumpFromPos - (iJumpFrom*10);

			playingField = new PlayingField(ctx, Game.coinBucket[iJumpTo][jJumpTo], Game.coinBucket[iJumpFrom][jJumpFrom]);
			placeCoinTime  = playingField.placeCoin(CoinBucket.ANDROY_COIN_STATE);
			
			return placeCoinTime;
			
		}
		
	}

}