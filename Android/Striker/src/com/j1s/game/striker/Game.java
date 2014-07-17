package com.j1s.game.striker;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.yrkfgo.assxqx4.AdListener;
import com.yrkfgo.assxqx4.AdListener.AdType;
import com.yrkfgo.assxqx4.AdView;
import com.yrkfgo.assxqx4.MA;

public class Game extends Activity{
	
	private int GAME_SIZE_X;
	private int GAME_SIZE_Y;
	private int GAME_COIN_HEIGHT;
	private int GAME_COIN_WIDTH;
	
	public final static int U_SCORE_ID = 1001;
	public final static int A_SCORE_ID = 1002;

	public final Context ctx = this;

	private AndroyMove androyMove;
	private PlayerMove playerMove;
	private int uScore = 0;
	private int aScore = 0;
	private int emptyCoinCount=0;
	private boolean displayResult = false;
	public static CoinBucket coinBucket[][];
	
	/*private AdView adView;*/
	/*private String MY_AD_UNIT_ID="a15033122c77011"; */
	
	
	private AdType adtype;
	LinearLayout layout;
	AdView adView;

	AdView ads;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	Log.d("Jones", "Entered onCreate...");
    	GAME_SIZE_X = State.getGameSizeX();
    	GAME_SIZE_Y = State.getGameSizeY();
    	GAME_COIN_HEIGHT = State.getGameCoinHeight();
    	GAME_COIN_WIDTH  = State.getGameCoinWidth();
    	coinBucket = new CoinBucket[GAME_SIZE_X][GAME_SIZE_Y];
    	
    	androyMove = new AndroyMove();
    	playerMove = new PlayerMove();
    	
    	GameLayout gameLayout 	= new GameLayout(this);
    	GameTabelLayout tLayout = new GameTabelLayout(this);
    	GameTabelLayout bgTLayout = new GameTabelLayout(this);
    	GameRowLayout rowLayout[] = new GameRowLayout[GAME_SIZE_Y];
    	GameRowLayout emptyRowLayout[] = new GameRowLayout[GAME_SIZE_Y];
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

/*		// Admob Banner : Starts --------------------------------------------------------------
		
        TableLayout bannerTableLayout = new TableLayout(this);
        bannerTableLayout.setStretchAllColumns(true);
        bannerTableLayout.setLayoutParams( 
        	new LayoutParams(LayoutParams.MATCH_PARENT
        			, LayoutParams.MATCH_PARENT));
        bannerTableLayout.setGravity(Gravity.TOP);
        gameLayout.addView(bannerTableLayout);
        
        adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
        bannerTableLayout.addView(adView);
        
        AdRequest adRequest = new AdRequest();
        
        //To test the add display
//        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);               // Emulator
//        adRequest.addTestDevice("TEST_DEVICE_ID");                      // Test Android Device
        // ---------------------------
        
        adView.loadAd(adRequest);
        
        // Admob Banner : Ends ------------------------------------------------------------------
*/        
		
        
		bgTLayout = getEmptyPlateFilledTable(bgTLayout, emptyRowLayout);
		bgTLayout.setGravity(Gravity.CENTER);
		tLayout = getInitialCoinFilledTable(tLayout, rowLayout);
		tLayout.setGravity(Gravity.CENTER);
		Log.d("Jones", "Crossed getFilledTable..."); 
		gameLayout.addView(bgTLayout);
        gameLayout.addView(tLayout);
        
        TableLayout ScoreTableLayout = new TableLayout(this);
        //ScoreTableLayout.setStretchAllColumns(true);
        ScoreTableLayout.setLayoutParams( 
        	new LayoutParams(LayoutParams.MATCH_PARENT
        			, LayoutParams.MATCH_PARENT));
        ScoreTableLayout.setGravity(Gravity.BOTTOM);
        gameLayout.addView(ScoreTableLayout);

        TableRow scoreRow = new TableRow(this);
        scoreRow.setGravity(Gravity.CENTER);
        scoreRow.setLayoutParams( 
        	new LayoutParams(LayoutParams.MATCH_PARENT
        			, LayoutParams.MATCH_PARENT));
        ScoreTableLayout.addView(scoreRow);
        
		calculateScore();
        
        TextView uScoreView = new TextView(this);
        uScoreView.setGravity(Gravity.LEFT);
        uScoreView.setBackgroundResource(R.drawable.background_image_score_heaven);
        uScoreView.setId(U_SCORE_ID);
        //uScoreView.setText("Jesus\n"+uScore);
        uScoreView.setText(""+uScore);
        uScoreView.setTextColor(Color.BLUE);
        uScoreView.setTextSize((float) 25.0);
        uScoreView.setGravity(Gravity.CENTER);
        scoreRow.addView(uScoreView);

        
        TextView aScoreView = new TextView(this);
        aScoreView.setGravity(Gravity.RIGHT);
        aScoreView.setBackgroundResource(R.drawable.background_image_score_hell);
        aScoreView.setId(A_SCORE_ID);
        //aScoreView.setText("Devil\n"+aScore);
        aScoreView.setText(""+aScore);
        aScoreView.setTextSize((float) 25.0);
        aScoreView.setTextColor(Color.BLACK);
        aScoreView.setGravity(Gravity.CENTER);
        scoreRow.addView(aScoreView);
        setContentView(gameLayout);
        
        
        Home.air.callLandingPageAd();
        Home.air.callSmartWallAd();
        
        layout = new LinearLayout(Game.this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setGravity(Gravity.TOP | Gravity.CENTER);

		Game.this.addContentView(layout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		adView = new AdView(Game.this, AdView.BANNER_TYPE_IN_APP_AD,
				AdView.PLACEMENT_TYPE_INTERSTITIAL, false, false,
				AdView.ANIMATION_TYPE_TOP_DOWN);
		layout.addView(adView);
        
    }
    
	private GameTabelLayout getInitialCoinFilledTable(GameTabelLayout tLayout,
			GameRowLayout[] rowLayout) {
		
		int SECOND_POS_VAL = 0;

		if (GAME_SIZE_X == 9 && GAME_SIZE_Y == 9){ 
			SECOND_POS_VAL = 2;		
		}
		if (GAME_SIZE_X == 6 && GAME_SIZE_Y == 6){
			SECOND_POS_VAL = 1;
		}

		for (int i = 0; i < GAME_SIZE_X; i++) {
			rowLayout[i] = new GameRowLayout(this);
			for (int j = 0; j < GAME_SIZE_Y; j++) {
				coinBucket[i][j] = new CoinBucket(this);
				coinBucket[i][j].setId((i*10)+j);
				if ((i == 0 && j == 0)
						|| (i == GAME_SIZE_X - 1 && j == GAME_SIZE_Y - 1)
						|| (i == SECOND_POS_VAL && j == SECOND_POS_VAL)
						|| (i == GAME_SIZE_X - SECOND_POS_VAL - 1 && j == GAME_SIZE_Y
								- SECOND_POS_VAL - 1)) {
					//if
					coinBucket[i][j].fillBucket(CoinBucket.PLAYER_COIN_STATE);
					
				} else if ((i == 0 && j == GAME_SIZE_Y - 1)
						|| (i == GAME_SIZE_X - 1 && j == 0)
						|| (i == SECOND_POS_VAL && j == GAME_SIZE_Y
								- SECOND_POS_VAL - 1)
						|| (i == GAME_SIZE_X - SECOND_POS_VAL - 1 && j == SECOND_POS_VAL)
					){
					//else if
					coinBucket[i][j].fillBucket(CoinBucket.ANDROY_COIN_STATE);
					
				} else {
					//else
					coinBucket[i][j].fillBucket(CoinBucket.EMPTY__COIN_STATE);
					
				}

				coinBucket[i][j].setOnClickListener(new View.OnClickListener() {

					public void onClick(View view) {						
												
						if(!State.isStillAcquiringEnemy() && !State.isAndroyTurn()){
							
							//Player plays here...
							int playerPlaceCoinTime;
							playerPlaceCoinTime = playerMove.placeCoin(ctx, (CoinBucket) view);
							
							State.setPlayerPlaceCoinTime(playerPlaceCoinTime);
							boolean isAndroyTurn = State.isAndroyTurn();
							
							if(isAndroyTurn){
								//Androy plays here...
								State.setPlayerTurn(false);
								Handler delayHandler = new Handler();
								//Calculating Score After player placed the coin
								delayHandler.postDelayed(new Runnable() {
									 
									public void run() {

										calculateScore();
										TextView uScoreV = (TextView) findViewById(U_SCORE_ID);
										//uScoreV.setText("Jesus\n"+uScore);
										uScoreV.setText(""+uScore);
										TextView aScoreV = (TextView) findViewById(A_SCORE_ID);
										//aScoreV.setText("Devil\n"+aScore);
										aScoreV.setText(""+aScore);
									}
								}, State.getPlayerPlaceCoinTime()+100);
								//Androy Trun here...
								delayHandler.postDelayed(new Runnable() {
									 
									public void run() {

										Handler delayHandler = new Handler();
										//Calling Androy to place a coin
										int androyPlaceCoinTime = androyMove.placeCoin(ctx);
										
										boolean noPlayerMoveAvailable = checkPlayerTurnAvailability();
										
										if(androyPlaceCoinTime == -1 || noPlayerMoveAvailable){
											androyPlaceCoinTime = 500;
											displayResult = true;
										}else
											displayResult = false;
										
										State.setAndroyPlaceCoinTime(androyPlaceCoinTime);
										State.setPlayerTurn(true);
										State.setAndroyTurn(false);
										//To display the Score after Androy move and The result if the game ends...
										delayHandler.postDelayed(new Runnable() {
											 
											public void run() {

												Handler delayHandler = new Handler();
												//Calculating score...
												calculateScore();
												TextView uScoreV = (TextView) findViewById(U_SCORE_ID);
												//uScoreV.setText("Jesus\n"+uScore);
												uScoreV.setText(""+uScore);
												TextView aScoreV = (TextView) findViewById(A_SCORE_ID);
												//aScoreV.setText("Devil\n"+aScore);
												aScoreV.setText(""+aScore);
												
												//Display result if game Ends...
												if(emptyCoinCount == 0 || uScore == 0 || aScore == 0 || displayResult == true){
													//Game Ends...
													delayHandler.postDelayed(new Runnable() {
														 
														private AlertDialog alertDialog;
														private ImageView view = new ImageView(ctx);
														
														public void run() {

															if(uScore > aScore ){
																view.setBackgroundResource(R.drawable.won);
																alertDialog = new AlertDialog.Builder(ctx)
																.setTitle(R.string.winner_title)
																.setIcon(R.drawable.icon)
																.setView(view)
																.setItems(R.array.home, 
																		new DialogInterface.OnClickListener() {
																			public void onClick(DialogInterface dialoginterface,
																					int i) {
																				finish();
																				Intent intent = new Intent(Game.this, Home.class);																				
																				startActivity(intent);
																			}
																		}).show();
																
															}else{
																// Androy wins
																view.setBackgroundResource(R.drawable.lost);
																alertDialog = new AlertDialog.Builder(ctx)
																.setTitle(R.string.winner_title)
																.setIcon(R.drawable.icon)
																.setView(view)
																.setItems(R.array.home,
																		new DialogInterface.OnClickListener() {
																			public void onClick(DialogInterface dialoginterface,
																					int i) {
																				finish();
																				Intent intent = new Intent(Game.this, Home.class);																				
																				startActivity(intent);
																			}
																		}).show();
															}	
															
														}
													}, 500);
												}
												
											}
										}, State.getAndroyPlaceCoinTime()+100);
										
									}
								}, State.getPlayerPlaceCoinTime()+1000);
								
								

							}
						}
					}

				});
				rowLayout[i].addView(coinBucket[i][j]);
				rowLayout[i].bringChildToFront(coinBucket[i][j]);
			}
			tLayout.addView(rowLayout[i]);
		}
		return tLayout;
	}

	public GameTabelLayout getEmptyPlateFilledTable(GameTabelLayout tLayout,
			GameRowLayout[] rowLayout){
		
		for (int i = 0; i < GAME_SIZE_X; i++) {
			rowLayout[i] = new GameRowLayout(this);
			for (int j = 0; j < GAME_SIZE_Y; j++) {
				ImageView iv = new ImageView(this);
				iv.setAdjustViewBounds(true);
				iv.setMaxHeight(GAME_COIN_HEIGHT);
				iv.setMaxWidth(GAME_COIN_WIDTH);
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
				iv.setImageResource(CoinBucket.EMPTY__COIN_ID);
				rowLayout[i].addView(iv);
			}
			tLayout.addView(rowLayout[i]);
		}
		return tLayout;
	}
	
	public void calculateScore(){
		aScore = 0;
		uScore = 0;
		emptyCoinCount = 0;
		for (int i = 0; i < GAME_SIZE_X; i++) {
			for (int j = 0; j < GAME_SIZE_Y; j++) {
				if(coinBucket[i][j].getState() == CoinBucket.ANDROY_COIN_STATE)
					aScore++;
				if(coinBucket[i][j].getState() == CoinBucket.PLAYER_COIN_STATE)
					uScore++;
				if(coinBucket[i][j].getState() == CoinBucket.EMPTY__COIN_STATE)
					emptyCoinCount++;
			}
		}
	}
	
	public boolean checkPlayerTurnAvailability(){

		for (int i = 0; i < GAME_SIZE_X; i++) {
			for (int j = 0; j < GAME_SIZE_Y; j++) {
				
				if(Game.coinBucket[i][j].getState() == CoinBucket.EMPTY__COIN_STATE) {
    				for(int x=i-1; x<=i+1; x++) {
    		    		for(int y=j-1; y<=j+1; y++) {
    		    			if((x>=0 && x<GAME_SIZE_X) && (y>=0 && y<GAME_SIZE_Y)) {
    		    				if(!(x==i && y==j)){

    		    					if(Game.coinBucket[x][y].getState() == CoinBucket.PLAYER_COIN_STATE)
	    		    					return false;
    		    					
    		    				}
    		    			}
    		    		}
    		    	}
    				
					for(int x=i-2; x<=i+2; x+=2){
						for(int y=j-2; y<=j+2; y+=2){
							if((x>=0 && x<GAME_SIZE_X) && (y>=0 && y<GAME_SIZE_Y)) {
								if(!(x==i && y==j)){
									
    								if(Game.coinBucket[x][y].getState() == CoinBucket.PLAYER_COIN_STATE){
    									return false;
    									
    								}
								}
							}
						}
					}
    			}
			}
		}
		
		return true;
	}
	
	
	
}