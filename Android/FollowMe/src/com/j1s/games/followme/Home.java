package com.j1s.games.followme;

import java.util.ArrayList;
import java.util.Random;

/*import com.airpush.android.Airpush;
import com.google.ads.AdRequest;*/

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends Activity implements OnTouchListener{
    
	public static Context context;
	private Dialog dialog;
	public static GridView gridview;
	public static ArrayList<Integer> path;
	public static int levels=3;
	

	/** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Home.context = Home.this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//if(Integer.parseInt(VERSION.SDK) > 3){
			//new Airpush(getApplicationContext(),"20961","airpush");
//			AdRequest adRequest = new AdRequest();
//			adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		//}
        setContentView(R.layout.home);
        
        WindowManager mWinMgr = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        int grdWidth  = mWinMgr.getDefaultDisplay().getWidth();
        int grdHeight = (mWinMgr.getDefaultDisplay().getHeight())-96; 
         
        gridview = (GridView) findViewById(R.id.gridview);
        ImageAdapter imgAdapter = new ImageAdapter(this);
        gridview.setAdapter(imgAdapter);
        
        
        path = new ArrayList<Integer>();
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setTitle(R.string.starttext);
        dialog.setCancelable(true);
        View startBut = dialog.findViewById(R.id.startbut);
        startBut.setOnTouchListener(this);
        dialog.show();
        
    }
    
    
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	levels=3;
    	super.onDestroy();
    	
    }
    
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			System.out.println("In On Touch------");
			Log.d("jones", "In On Touch----");
			startPlay(levels);			
			dialog.dismiss();			
			return true;
		}
		return false;
	}
	
	public void startPlay(final int times){
		Toast.makeText(Home.context, "Level "+(Home.levels-2), 500).show();
		int delay = 2500;
		final Random rand = new Random();
		if(!path.isEmpty()) path.clear();
		//disableButtons(gridview);
		/*for(int i=1; i<=times; i++ ){

			System.out.println("In For Loop------"+i);
			Log.d("jones", "In For Loop----"+i);
			final int j = i;
			Handler delayHandler = new Handler();
			delayHandler.postDelayed(new Runnable() {
				
				public void run() {
					
					final int index = rand.nextInt(8);
					path.add(j-1, index);
					ImageView imgView = (ImageView) Home.gridview.getChildAt(index);
					System.out.println("-------"+j);
					Log.d("jones", "----"+j);
					final MediaPlayer mp = SoundManager.playSound(imgView, index);				
					Handler innerDelayHandler = new Handler();
					innerDelayHandler.postDelayed(new Runnable() {
						
						
						public void run() {
							ImageView imgView = (ImageView) Home.gridview.getChildAt(index);
							SoundManager.stopSound(imgView, mp, index);
							if(j==times){
								enableButtons(gridview);
							}
											
						}
						
					}, 100+rand.nextInt(1000));	
				}
				
			}, delay);
			delay = delay+800;
			
				
		}*/
		
	}
	
	public void disableButtons(GridView gridView){
		ArrayList<View> viewsInGrid =  gridView.getTouchables();
		gridView.setEnabled(false);
		for(View v : viewsInGrid){
			v.setEnabled(false);
		}		
	}
	
	public void enableButtons(GridView gridView){
		gridView.setEnabled(true);
		for(int i=0; i<9;i++){
			View v = gridView.getChildAt(i);
			v.setEnabled(true);
		}
		
	}
	
}