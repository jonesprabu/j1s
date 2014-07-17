package com.j1s.games.trapthefrog;

import android.media.MediaPlayer;



public class SoundManager {
	
	 private static  MediaPlayer mp[] = {
    		 MediaPlayer.create(CircleTheFrog.ctx, SoundAdapter.sounds[0])
            ,MediaPlayer.create(CircleTheFrog.ctx, SoundAdapter.sounds[1])
            ,MediaPlayer.create(CircleTheFrog.ctx, SoundAdapter.sounds[2])
            ,MediaPlayer.create(CircleTheFrog.ctx, SoundAdapter.sounds[3])
    };	
	 
		 
	public static MediaPlayer playSound(int index){
		mp[index] = MediaPlayer.create(CircleTheFrog.ctx, SoundAdapter.sounds[index]);
		mp[index].start();
		return mp[index];
	} 
	
	public static void stopSound(MediaPlayer mp, int index) {

		mp.stop();
		mp.release();
		mp = null;
	}
}