package com.j1s.games.frogjump;

import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundAdapter {

	public static int[] sounds = {
		R.raw.frogcroak, R.raw.cheering,
		R.raw.waves, R.raw.stonedrown
	};
	
	public static final int FROGCROAK = 0;
	public static final int CHEERING = 1;
	public static final int WAVES = 2;
	public static final int SPLASH = 3;
	
}
