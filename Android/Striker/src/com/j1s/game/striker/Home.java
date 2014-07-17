package com.j1s.game.striker;


import com.yrkfgo.assxqx4.AdListener;
import com.yrkfgo.assxqx4.AdView;
import com.yrkfgo.assxqx4.MA;
import com.yrkfgo.assxqx4.AdListener.AdType;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class Home extends Activity implements OnClickListener, AdListener {
	
	
	public Context thisCtx;
	
	static MA air = null;
	private AdType adtype;
	LinearLayout layout;
	AdView adView;

	AdView ads;
		
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//new Airpush(getApplicationContext(), "65421", "1318449575701366408", true, true, true);

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		/*>>>>>>> Air Push New SDK Changes*/
		air = new MA(this, this, true);		
		/* Air Push New SDK Changes <<<<<<<<<*/

		setContentView(R.layout.main);
		
		Home.air.callSmartWallAd();
        
        layout = new LinearLayout(Home.this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setGravity(Gravity.TOP | Gravity.CENTER);
		
		Home.this.addContentView(layout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		adView = new AdView(Home.this, AdView.BANNER_TYPE_IN_APP_AD,
				AdView.PLACEMENT_TYPE_INTERSTITIAL, false, false,
				AdView.ANIMATION_TYPE_LEFT_TO_RIGHT);
		layout.addView(adView);
		
		State.setHomeClass(Home.this);
		// Set up click listeners for all the buttons
		Button newButton = (Button) findViewById(R.id.new_button);
		newButton.setOnClickListener(this);
		Button aboutButton = (Button) findViewById(R.id.about_button);
		aboutButton.setOnClickListener( this);
		Button exitButton = (Button) findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.about_button:
			Intent abt = new Intent(this, About.class);
			startActivity(abt);
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
	
	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.new_game_title)
		.setIcon(R.drawable.icon)
		.setItems(R.array.levels,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface,
							int i) {
						startGame(i);
						
					}
				}).show();
	}
	
	private void startGame(int choice) {
		Intent i;
		switch (choice) {
		case 0:
			finish();
			setGameLevel(6, 6);
			i = new Intent(Home.this, Game.class);
			startActivity(i);
			Log.d("Jones", "Entered ---- "+choice);
			break;
		
		case 1:
			finish();
			setGameLevel(7, 7);
			i = new Intent(Home.this, Game.class);
			startActivity(i);
			Log.d("Jones", "Entered ---- "+choice);
			break;
		
		case 2:
			finish();
			setGameLevel(8, 8);
			i = new Intent(Home.this, Game.class);
			startActivity(i);
			Log.d("Jones", "Entered ---- "+choice);
			break;
		
		case 3:
			finish();
			setGameLevel(9, 9);
			i = new Intent(Home.this, Game.class);
			startActivity(i);
			Log.d("Jones", "Entered ---- "+choice);
			break;
		}

	}
	
	public void setGameLevel(int x, int y){
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;

		State.setGameSizeX(x);
		State.setGameSizeY(y);
		State.setGameCoinHeight(((width-55)/x));
		State.setGameCoinWidth(((width-55)/y));
	}
	
	@Override
	public void onSDKIntegrationError(String arg0) {

		//Toast.makeText(this, "Airpush SDK integartion issue: " + arg0,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAdError(String arg0) {
		//Toast.makeText(this, "AD issue: " + arg0, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSmartWallAdShowing() {

		//Toast.makeText(this, "SmartWall Ad is showing.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSmartWallAdClosed() {

		//Toast.makeText(this, "SmartWall closed.", Toast.LENGTH_SHORT).show();
	}


	@Override
	public void noAdAvailableListener() {
		//Toast.makeText(getApplicationContext(), "Ad's Not Available", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onAdCached(AdListener.AdType arg0) {
		//Toast.makeText(this, "Ad cached: " + arg0, Toast.LENGTH_SHORT).show();
		adtype = arg0;
		
	}
	

	
	/*
	 * Caching Technique given for only "Smartwall Ad".
	 * Reference: At a time developer can show cached Ad for only one Ad Format i,e In this Example enabled caching feature for  "SmartWall Ad".   
	 */

	@Override
	public void onBackPressed()  {

			
			try {
				air.showCachedAd(this, AdType.smartwall);
			} catch (Exception e) 
			{
	
			super.onBackPressed();
			}
	}
	
}