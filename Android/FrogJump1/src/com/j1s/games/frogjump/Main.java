package com.j1s.games.frogjump;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.yrkfgo.assxqx4.AdListener;
import com.yrkfgo.assxqx4.AdView;
import com.yrkfgo.assxqx4.MA;

@SuppressLint("NewApi")
public class Main extends Activity implements OnTouchListener, AdListener{
	
	public int[] buttIds = {R.drawable.quit_button, R.drawable.reset_button, R.drawable.rules_button};
	public static int resultCount;
	public static Context ctx;
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	
	static MA air = null;
	private AdType adtype;
	LinearLayout layout;
	AdView adView;

	AdView ads;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        
        /*
         * Airpush---
         */

        /*if(Integer.parseInt(VERSION.SDK) > 3){
        	new Airpush( getApplicationContext(), "30775", "1318449575701366408", false, true, true);
        }*/
        air = new MA(this, this, true);
        
        
        Display display = getWindowManager().getDefaultDisplay(); 
        int SCREEN_WIDTH = display.getWidth();
        int SCREEN_HEIGHT = display.getHeight();

        AbsoluteLayout baseLayout = new AbsoluteLayout(this);
        baseLayout.setLayoutParams( new LayoutParams(LayoutParams.FILL_PARENT
				, LayoutParams.FILL_PARENT));
        baseLayout.setBackgroundResource(R.drawable.bg);
        
        /*TextView txtView = new TextView(this);
        txtView.setText("                                                                                Click the add and view to get Solution.");
        txtView.setTextColor(Color.RED);
        txtView.setGravity(Gravity.RIGHT);*/

        
        // Create the adView
//        AdView adView = new AdView(this, AdSize.BANNER, "a14e97aa2d0b0a9");
//        adView.setLayoutParams(new LayoutParams(150, 40, 100, 40));
//        adView.setOnTouchListener(new OnTouchListener() {
//			
//			public boolean onTouch(View v, MotionEvent event) {
//				System.out.println("Clicked the add....");				
//				return v.onTouchEvent(event);
//			}
//		});
       
        // Add the adView to it
        /*baseLayout.addView(adView);
        baseLayout.addView(txtView);
        // Initiate a generic request to load it with an ad
        adView.loadAd(new AdRequest());*/

        
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setLayoutParams( 
        		new LayoutParams(LayoutParams.FILL_PARENT
        				, LayoutParams.FILL_PARENT));
        tableLayout.setGravity(Gravity.CENTER);
        
        TableRow tableRow1Layout = new TableRow(this);
        tableRow1Layout.setGravity(Gravity.CENTER_HORIZONTAL);
        tableRow1Layout.setLayoutParams( 
        		new LayoutParams(LayoutParams.FILL_PARENT
        				, LayoutParams.FILL_PARENT));
        
        FrogJumpListeners.emptyPos = 3;
		FrogJumpListeners.stones = new int[] {0,1,2,3,4,5,6};
        
        for(int i=0; i<7; i++){
        	ImageView frog = new ImageView(this);
        	
        	frog.setAdjustViewBounds(true);
        	frog.setMaxWidth(60);
        	frog.setScaleType(ImageView.ScaleType.FIT_CENTER);
        	        	       	
        	if(i<3){
        		frog.setImageResource(R.drawable.frog_left);
        	}
        	else if(i>3){
        		frog.setImageResource(R.drawable.frog_right);
        	}
        	else{
        		frog.setImageResource(R.drawable.empty_stone);
        	}
        	frog.setId(i);
        	frog.setOnTouchListener(new FrogJumpListeners());

           	tableRow1Layout.addView(frog, i);
    	}
        tableLayout.addView(tableRow1Layout);
        baseLayout.addView(tableLayout);
        
        TableLayout tableLayout2 = new TableLayout(this);
        tableLayout2.setStretchAllColumns(true);
        tableLayout2.setLayoutParams( 
        		new LayoutParams(LayoutParams.FILL_PARENT
        				, LayoutParams.FILL_PARENT));
        tableLayout2.setGravity(Gravity.BOTTOM);
        
	    TableRow tableRow2Layout = new TableRow(this);
	    tableRow2Layout.setGravity(Gravity.CENTER_HORIZONTAL);
	    tableRow2Layout.setLayoutParams( 
	    		new LayoutParams(LayoutParams.WRAP_CONTENT
	  				, LayoutParams.WRAP_CONTENT));
	    for(int i=0; i<3; i++){
        	ImageView butt = new ImageView(this);
        	butt.setId(buttIds[i]);
        	butt.setAdjustViewBounds(true);
        	butt.setImageResource(buttIds[i]);
        	butt.setOnTouchListener(this);
           	tableRow2Layout.addView(butt, i);
    	}
	    tableLayout2.addView(tableRow2Layout);
	    baseLayout.addView(tableLayout2);
	    
//	    AdView adView1 = (AdView) findViewById(R.id.adView1);
//	    adView1.setOnTouchListener(new OnTouchListener() {
//			
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});
	    
        setContentView(baseLayout);
        
        air.callLandingPageAd();
        
        
        layout = new LinearLayout(Main.this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setGravity(Gravity.TOP);
		Main.this.addContentView(layout, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		adView = new AdView(Main.this, AdView.BANNER_TYPE_IN_APP_AD,
				AdView.PLACEMENT_TYPE_INTERSTITIAL, false, false,
				AdView.ANIMATION_TYPE_LEFT_TO_RIGHT);
		layout.addView(adView);
		
		
     
    }
    
        
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(view.getId() == buttIds[0])
				view.setImageResource(R.drawable.clicked_quit_butt);
			if(view.getId() == buttIds[1])
				view.setImageResource(R.drawable.clicked_reset_button);
			if(view.getId() == buttIds[2])
				view.setImageResource(R.drawable.clicked_rules_but);
			return true;
		}
		
		if(event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
			if(view.getId() == buttIds[0]){
				view.setImageResource(R.drawable.quit_button);
				finish();
				FrogJumpListeners.emptyPos = 3;
				FrogJumpListeners.stones = new int[] {0,1,2,3,4,5,6};
			}
			if(view.getId() == buttIds[1]){
				view.setImageResource(R.drawable.reset_button);
				reset();
			}
			if(view.getId() == buttIds[2]){
				view.setImageResource(R.drawable.rules_button);
				
				@SuppressWarnings("unused")
				AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setIcon(R.drawable.icon)
				.setCancelable(true)
				.setInverseBackgroundForced(false)
				.setMessage(R.string.rules)
				.setNegativeButton("Back", new OnClickListener() {   
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				})
				.show();
				
				return false;
			}
			
		}
		return false;
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
	
	public void reset(){
		finish();
		Intent reset = new Intent(this, Main.class);
		FrogJumpListeners.emptyPos = 3;
		FrogJumpListeners.stones = new int[] {0,1,2,3,4,5,6};
		startActivity(reset);
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