package com.j1s.game.striker;




import android.content.Context;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class PlayingField {
	
	private int GAME_SIZE_X;
	private int GAME_SIZE_Y;
	private Context ctx;
	private CoinBucket newCoin;
	private CoinBucket jumpFrom;
	private int PLACE_COIN_TIME = 500; 
	private Handler delayHandler = new Handler();
	
	public PlayingField(Context ctx, CoinBucket newCoin, CoinBucket jumpFrom) {
		// TODO Auto-generated constructor stub
		this.ctx = ctx;
		this.newCoin = newCoin;
		this.jumpFrom = jumpFrom;
		this.GAME_SIZE_X = State.getGameSizeX();
		this.GAME_SIZE_Y = State.getGameSizeY();
	}
	
	public int placeCoin(final int coinState){
		int coinId = newCoin.getId();
		int placeCoinTime = PLACE_COIN_TIME;
		boolean canFill = true;
		if(coinState == CoinBucket.PLAYER_COIN_STATE)
			canFill = checkOwnNeighbour(coinId, coinState);
		State.setAndroyTurn(canFill);

		if(canFill){
			if(coinState == CoinBucket.ANDROY_COIN_STATE) {

				placeCoinTime = hiLiteNewPlace(newCoin.getId());
				delayHandler.postDelayed(new Runnable() {

					public void run() {
						if(jumpFrom != null){
							jumpFrom.fillBucket(CoinBucket.EMPTY__COIN_STATE);
						}
						newCoin.fillBucket(coinState);
					}
					
				},placeCoinTime+500);
				placeCoinTime = placeCoinTime+500;
				placeCoinTime = acquireEnemyNeighbour(coinId, coinState, placeCoinTime);
			}
						
			if(coinState == CoinBucket.PLAYER_COIN_STATE){
				newCoin.fillBucket(coinState);
				placeCoinTime = acquireEnemyNeighbour(coinId, coinState, 0);
			}
			
			if(coinState == CoinBucket.EMPTY__COIN_STATE){
				newCoin.fillBucket(coinState);
				return 0;
			}
			
		}
		return placeCoinTime;
	}
	
	public int hiLiteNewPlace(int id) {

//		final int x = id/10;
//		final int y = id-(x*10);
		int delaytime = 100;
		for(int i = 1; i<=10; i++) {
			if(i%2==0){
				delayHandler.postDelayed(new Runnable() {
	
					public void run() {
						if(jumpFrom != null){
							//jumpFrom.setImageResource(R.drawable.androy_coin);
							jumpFrom.setImageResource(CoinBucket.ANDROY_COIN_ID);
						}
						//Game.coinBucket[x][y].setImageResource(R.drawable.empty_plate);
						newCoin.setImageResource(R.drawable.empty_plate);
					}
					
				},delaytime*i);
			}else{
				delayHandler.postDelayed(new Runnable() {
					
					public void run() {
						if(jumpFrom != null){
							jumpFrom.setImageResource(R.drawable.full_empty_plate);
						}
						//Game.coinBucket[x][y].setImageResource(R.drawable.highlighted_empty_plate);
						newCoin.setImageResource(R.drawable.full_empty_plate);
					}
					
				},delaytime*i);
			}			
		}
		return delaytime*10;
	}

	public boolean checkOwnNeighbour(int id, int coinState){
		int x = id/10;
		int y = id - (x*10);
		
		for(int i = x-1; i <= x+1; i++){
			for(int j = y-1; j <= y+1; j++){

				if((i>=0 && i<GAME_SIZE_X) && (j>=0 && j<GAME_SIZE_Y) 
					&& (Game.coinBucket[i][j].getState() == coinState)){
						return true;
				}
			}
		}
		return false;
	}
	
	public int acquireEnemyNeighbour(int id, final int ownCoinState, int delayTime){
		int enemyCoinState = ownCoinState==CoinBucket.ANDROY_COIN_STATE ? CoinBucket.PLAYER_COIN_STATE:CoinBucket.ANDROY_COIN_STATE;
		int x = id/10;
		int y = id - (x*10);
		int mult = 1;

		for(int i = x-1; i <= x+1; i++){
			for(int j = y-1; j <= y+1; j++){
				if((i>=0 && i<GAME_SIZE_X) && (j>=0 && j<GAME_SIZE_Y) 
						&& (Game.coinBucket[i][j].getState() == enemyCoinState)){
						final int ii = i;
						final int jj = j;
						State.setStillAcquiringEnemy(true);
							delayHandler.postDelayed(new Runnable() {
	
								public void run() {
									// TODO Auto-generated method stub
									Game.coinBucket[ii][jj].fillBucket(ownCoinState);
									runFadeOutAnimationOn(ctx, Game.coinBucket[ii][jj]);
								}
							}, delayTime+(PLACE_COIN_TIME*(mult++)));
						    					
    			}
    		}
    	}
		
		delayHandler.postDelayed(new Runnable() {
 
			public void run() {
				// TODO Auto-generated method stub
				State.setStillAcquiringEnemy(false);
			}
		}, delayTime+(PLACE_COIN_TIME*(mult++)));
		
		return delayTime+(PLACE_COIN_TIME*(mult++));
	}


	public Animation runFadeOutAnimationOn(Context ctx, CoinBucket target) {
	  Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.picket_born_anim);
	  target.startAnimation(animation);
	  return animation;
	}
	
	public boolean setPlayerJumpingCells(int iPos, int jPos, boolean isMarkCells){
		boolean markedEmptyFlag = false;
		for(int i=iPos-2; i<=iPos+2; i+=2){
    		for(int j=jPos-2; j<=jPos+2; j+=2){
    			if((i>=0 && i<GAME_SIZE_X) && (j>=0 && j<GAME_SIZE_Y)){
    				if(isMarkCells && Game.coinBucket[i][j].getState() == CoinBucket.EMPTY__COIN_STATE){
    					markedEmptyFlag = true;
    	    			markEmptyCell(i, j);
    				} else if(!isMarkCells && Game.coinBucket[i][j].getState() == CoinBucket.SELECTED_COIN_STATE){
    	    			clearMarkedCell(i, j);
    	    		}    			
    			}
    		}
    	}
		return markedEmptyFlag;
	}
	
	public void markEmptyCell(int i, int j){
    	
    	Game.coinBucket[i][j].fillBucket(CoinBucket.SELECTED_COIN_STATE);
    	
	}
    
    public void clearMarkedCell(int i, int j){
    	
    	Game.coinBucket[i][j].fillBucket(CoinBucket.EMPTY__COIN_STATE);
    	
    }
	
}