package com.j1s.games.frogjump;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.ImageView;



public class SoundManager {
	
	 private static  MediaPlayer mp[] = {
    		 MediaPlayer.create(Main.ctx, SoundAdapter.sounds[0])
            ,MediaPlayer.create(Main.ctx, SoundAdapter.sounds[1])
            ,MediaPlayer.create(Main.ctx, SoundAdapter.sounds[2])
            ,MediaPlayer.create(Main.ctx, SoundAdapter.sounds[3])
    };	
	 
		 
	public static MediaPlayer playSound(int index){
		mp[index] = MediaPlayer.create(Main.ctx, SoundAdapter.sounds[index]);
		mp[index].start();
		return mp[index];
	} 
	
	public static void stopSound(MediaPlayer mp, int index) {

		mp.stop();
		mp.release();
		mp = null;
	}
}