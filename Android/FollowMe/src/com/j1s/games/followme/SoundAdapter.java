package com.j1s.games.followme;

import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundAdapter {

	public static int[] sounds = {
		R.raw.sound1, R.raw.sound2,
		R.raw.sound5, R.raw.sound4,
		R.raw.sound5, R.raw.sound1,
		R.raw.sound2, R.raw.sound4, R.raw.sound5
	};
	
	public static MediaPlayer mPalyer = new MediaPlayer();
	public static SoundPool sPool 	= new SoundPool(2, 3, 0);
}
