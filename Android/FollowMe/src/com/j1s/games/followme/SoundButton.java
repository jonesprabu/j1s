package com.j1s.games.followme;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

public class SoundButton extends ImageView{
	
	private Context mContext;
	
	WindowManager mWinMgr;
    int screenWIDTH;
    int imageWidth;
    int imageHeight;
    
    private MediaPlayer mediaPlayer;

	public SoundButton(Context context, int id) {
		super(context);
		mContext = context;
		mWinMgr = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
	    screenWIDTH  = mWinMgr.getDefaultDisplay().getWidth();
	    imageWidth  = screenWIDTH/3;
	    imageHeight = imageWidth;
	    
	    this.setLayoutParams(new GridView.LayoutParams(imageWidth, imageHeight));
        this.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Log.d("jones", "setId >>>>>>>>>> "+id);
        this.setId(id);
        this.setImageResource(R.drawable.button);
        this.setOnTouchListener(new OnSoundButtonTouchListener());
        this.mediaPlayer = MediaPlayer.create(mContext, SoundAdapter.sounds[id]);
	}
	


	private class OnSoundButtonTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			SoundButton soundButton = (SoundButton) v;
			soundButton.setImageResource(R.drawable.clickedbutton);
			MediaPlayer mediaPlayer = soundButton.mediaPlayer;
			mediaPlayer.start();
			mediaPlayer.setOnCompletionListener(new OnSoundButtonSoundCompletionListener(soundButton));
			return true;
		}
		
		
		private class OnSoundButtonSoundCompletionListener implements OnCompletionListener{
			
			SoundButton soundButton;
			
			public OnSoundButtonSoundCompletionListener(SoundButton soundButton) {
				this.soundButton = soundButton;
			}

			@Override
			public void onCompletion(MediaPlayer mp) {
				soundButton.setImageResource(R.drawable.button);
				mp.stop();
				try {
					mp.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	

}
