package com.j1s.games.followme;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.ImageView;



public class SoundManager {
	
	private static  SoundPool mSoundPool; 
	private static  HashMap<Integer, Integer> mSoundPoolMap; 
	private static  AudioManager  mAudioManager;
	private static  int streamId;
	
	 private static  MediaPlayer mp[] = {
    		 MediaPlayer.create(Home.context, SoundAdapter.sounds[0])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[1])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[2])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[3])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[4])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[5])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[6])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[7])
            ,MediaPlayer.create(Home.context, SoundAdapter.sounds[8])
    };	
	 
	public static void init(){
		 mSoundPool 	= new SoundPool(4, AudioManager.STREAM_MUSIC, 0); 
	     mSoundPoolMap 	= new HashMap<Integer, Integer>(); 
	     mAudioManager 	= (AudioManager)Home.context.getSystemService(Context.AUDIO_SERVICE);
	     int i=0;
	     for(int sId : SoundAdapter.sounds){
	    	 mSoundPoolMap.put(i++, mSoundPool.load(Home.context, sId, i++));
	     }
	}
	 
	public static MediaPlayer playSound(ImageView imgView, int index){
		imgView.setImageResource(R.drawable.clickedbutton);
		mp[index] = MediaPlayer.create(Home.context, SoundAdapter.sounds[index]);
		mp[index].start();
		return mp[index];
	} 
	
	public static void stopSound(ImageView imgView, int index){
		imgView.setImageResource(R.drawable.button);
		mp[index].stop();
		mp[index].release();
		mp[index] = null;
	}
	
	public static int playAutoSound(ImageView imgView, int index) { 
		
	    //int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
	    imgView.setImageResource(R.drawable.clickedbutton);
	    return mSoundPool.play(mSoundPoolMap.get(index), 1, 1, 1, 0, 1f); 
	     
	}
	
	public static void stopAutoSound(ImageView imgView, int streamId) { 
		imgView.setImageResource(R.drawable.button);
	    mSoundPool.stop(streamId); 
	}

	public static void stopSound(ImageView imgView, MediaPlayer mp, int index) {
		// TODO Auto-generated method stub0
		imgView.setImageResource(R.drawable.button);
		mp.stop();
		mp.release();
		mp = null;
	}
}