package com.j1s.games.frogjump;

import com.yrkfgo.assxqx4.MA;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TableRow;

public class FrogJumpListeners extends Activity implements OnTouchListener {
	
	public static int emptyPos;
	public static int stones[] = {0,1,2,3,4,5,6};	
	public int result[] = {4,5,6,3,0,1,2}; 
	private int[] jump4mRight = {R.drawable.j1r, R.drawable.j2r, R.drawable.j3r, R.drawable.j4r, R.drawable.j5r, R.drawable.j6r, R.drawable.frog_right };
	private int[] jump4mLeft  = {R.drawable.j1l, R.drawable.j2l, R.drawable.j3l, R.drawable.j4l, R.drawable.j5l, R.drawable.j6l, R.drawable.frog_left };
	
	public boolean onTouch(View v, MotionEvent event) {
		
		Log.d("J1S", "Enrtered onTouch....");
		final ImageView touchedView = (ImageView)v;
		int touchedPos = touchedView.getId();
		TableRow tableRow = (TableRow) touchedView.getParent();
		
		Handler delayHandler = new Handler();
				
		if(stones[touchedPos] < 3){ // To Jump towards Right from left
			if(touchedPos<emptyPos && emptyPos-touchedPos < 3){
				stones[emptyPos] = stones[touchedPos];
				stones[touchedPos] = 3;

				final MediaPlayer mp = SoundManager.playSound(SoundAdapter.FROGCROAK);
				
				final ImageView emptyStoneView = (ImageView) tableRow.getChildAt(emptyPos);
			
				for(int i=0; i<8; i++){
					final int j = i;
					delayHandler.postDelayed(new Runnable() {
						
						public void run() {
							// TODO Auto-generated method stub
													
							if(j<3){
								touchedView.setImageResource(jump4mLeft[j]);
							}else if(j<7){
								touchedView.setImageResource(R.drawable.empty_stone);
								emptyStoneView.setImageResource(jump4mLeft[j]);
							}else{
								SoundManager.stopSound(mp, SoundAdapter.FROGCROAK);
							}
						}
					}, 100*(j+1));
				}
				
				emptyPos = touchedPos;
			}					
		}else if(stones[touchedPos] > 3){  // To Jump towards Left From Right
			if(touchedPos>emptyPos && touchedPos-emptyPos < 3){
				stones[emptyPos] = stones[touchedPos];
				stones[touchedPos] = 3;
				
				final MediaPlayer mp = SoundManager.playSound(SoundAdapter.FROGCROAK);
				final ImageView emptyStoneView = (ImageView) tableRow.getChildAt(emptyPos);
				
				for(int i=0; i<8; i++){
					final int j = i;
					delayHandler.postDelayed(new Runnable() {
						
						public void run() {
							// TODO Auto-generated method stub
													
							if(j<3){
								touchedView.setImageResource(jump4mRight[j]);
							}else if(j<7){
								touchedView.setImageResource(R.drawable.empty_stone);
								emptyStoneView.setImageResource(jump4mRight[j]);  
							}else{
								SoundManager.stopSound(mp, SoundAdapter.FROGCROAK);
							}
						}
					}, 100*(j+1));
				}
				
				emptyPos = touchedPos;
			}	
		}
		
		int resultCount = 0;
		
		for(int i=0; i<7; i++){
			if(stones[i] == result[i])
				resultCount++;
		}
		if(resultCount == 7){
			delayHandler.postDelayed(new Runnable() {
				public void run() {
				
				final MediaPlayer mp = SoundManager.playSound(SoundAdapter.CHEERING);
				@SuppressWarnings("unused")
				AlertDialog alertDialog;
				alertDialog = new AlertDialog.Builder(Main.ctx)
				.setIcon(R.drawable.icon)
				.setItems(R.array.home,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface,
								int i) {
							SoundManager.stopSound(mp, SoundAdapter.CHEERING);
							dialoginterface.dismiss();
							Main.air.callSmartWallAd();
						}
					}).show();
				}
			}, 800);
		}
		
		return false;
	}

}
