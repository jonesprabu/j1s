package com.j1s.game.striker;

import android.content.Context;

public class PlayerMove {
	
	private PlayingField playingField;
	private int jumpFlag = 0;
	private int lastSelectedId = -1;
	
	public int placeCoin(Context ctx, CoinBucket newCoin){
		playingField = new PlayingField(ctx, newCoin, null);
		int newCoinId = newCoin.getId();
		int iNewCoinId = newCoinId/10;
		int jNewCoinId = newCoinId-(iNewCoinId*10);
		int placeCoinTime  = 500;

		if(Game.coinBucket[iNewCoinId][jNewCoinId].getState() == CoinBucket.EMPTY__COIN_STATE && jumpFlag == 0) {

			placeCoinTime = playingField.placeCoin(CoinBucket.PLAYER_COIN_STATE);


		}else if(Game.coinBucket[iNewCoinId][jNewCoinId].getState() == CoinBucket.PLAYER_COIN_STATE && jumpFlag == 0){

			boolean markCells = true;
			boolean markedEmptyCells = playingField.setPlayerJumpingCells(iNewCoinId, jNewCoinId, markCells);
			if(markedEmptyCells){
				jumpFlag = 1;
				lastSelectedId = newCoinId;
			}
    		State.setAndroyTurn(false);
    		
		}else if(Game.coinBucket[iNewCoinId][jNewCoinId].getState() == CoinBucket.SELECTED_COIN_STATE || lastSelectedId == newCoinId){
    		//Selected Jump position
	    	int iLastSelectedId = lastSelectedId/10;
	    	int jLastSelectedId = lastSelectedId-(iLastSelectedId*10);
	    	
	    	int currentselectedId = newCoinId;
	    	boolean isMarkCells = false;
	    	playingField.setPlayerJumpingCells(iLastSelectedId, jLastSelectedId, isMarkCells);
	    	playingField.clearMarkedCell(iLastSelectedId, jLastSelectedId);
	    	jumpFlag = 0;
    		newCoin.fillBucket(CoinBucket.PLAYER_COIN_STATE);
	    	if(lastSelectedId != currentselectedId){
	    		placeCoinTime = playingField.acquireEnemyNeighbour(newCoinId, CoinBucket.PLAYER_COIN_STATE, 0); //Modify
	    		State.setAndroyTurn(true);
	    	}else{
	    		State.setAndroyTurn(false);
	    	}
    	}
		
		return placeCoinTime;
				
	}
	
}
