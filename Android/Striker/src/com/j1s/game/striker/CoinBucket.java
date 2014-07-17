package com.j1s.game.striker;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CoinBucket extends ImageView {
	
	
	public static final int EMPTY__COIN_STATE = 0;
	public static final int PLAYER_COIN_STATE = 1;
	public static final int ANDROY_COIN_STATE = 2;
	public static final int SELECTED_COIN_STATE = 3;
	public static final int HIGHLIGHTED_COIN_STATE = 4;
	
	public static final int EMPTY__COIN_ID = R.drawable.empty_plate;
	public static final int FULL_EMPTY__ID = R.drawable.full_empty_plate;
	/*public static final int PLAYER_COIN_ID = R.drawable.player_coin;
	public static final int ANDROY_COIN_ID = R.drawable.androy_coin;*/
	public static final int PLAYER_COIN_ID = R.drawable.jesus_coin;
	public static final int ANDROY_COIN_ID = R.drawable.devil_coin;
	public static final int SELECTED_COIN_ID = R.drawable.selected_plate;
	public Drawable d;
	
	private int state; 
	private int playerCoinCount;
	private int jumpPosPlayerCoinCount;
	private ArrayList<Integer> possibleJumpFromPositions;
	private int neighbourAndroyCoinCount;
	

	public int getNeighbourAndroyCoinCount() {
		return neighbourAndroyCoinCount;
	}

	public void setNeighbourAndroyCoinCount(int neighbourAndroyCoinCount) {
		this.neighbourAndroyCoinCount = neighbourAndroyCoinCount;
	}

	public ArrayList<Integer> getPossibleJumpFromPositions() {
		return possibleJumpFromPositions;
	}

	public void setPossibleJumpFromPositions(
			ArrayList<Integer> possibleJumpFromPositions) {
		this.possibleJumpFromPositions = possibleJumpFromPositions;
	}

	public void clearPossibleJumpFromPositions(){
		this.possibleJumpFromPositions.clear();
	}
	
	public int getJumpPosPlayerCoinCount() {
		return jumpPosPlayerCoinCount;
	}

	public void setJumpPosPlayerCoinCount(int jumpPosPlayerCoinCount) {
		this.jumpPosPlayerCoinCount = jumpPosPlayerCoinCount;
	}

	public int getPlayerCoinCount() {
		return playerCoinCount;
	}

	public void setPlayerCoinCount(int playerCoinCount) {
		this.playerCoinCount = playerCoinCount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public CoinBucket(Context context) {
		super(context);
		super.setAdjustViewBounds(true);
		super.setMaxHeight(State.getGameCoinHeight());
		super.setMaxWidth(State.getGameCoinWidth());
		super.setScaleType(ImageView.ScaleType.FIT_CENTER);

		// TODO Auto-generated constructor stub
	}
	
	public boolean fillBucket(int state){
		switch (state) {
		case EMPTY__COIN_STATE:
			super.setImageResource(FULL_EMPTY__ID);
			this.setState(EMPTY__COIN_STATE);
			break;
		case PLAYER_COIN_STATE:
			super.setImageResource(PLAYER_COIN_ID);
			this.setState(PLAYER_COIN_STATE);
			break;
		case ANDROY_COIN_STATE:
			super.setImageResource(ANDROY_COIN_ID);	
			this.setState(ANDROY_COIN_STATE);
			break;
		case SELECTED_COIN_STATE:
			super.setImageResource(SELECTED_COIN_ID);
			this.setState(SELECTED_COIN_STATE);
			break;
		default:
			
			break;
		}
		
		return true;
	}

}
