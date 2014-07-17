package com.j1s.games.frogjump;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public class BootReceiver extends BroadcastReceiver {
	public void onReceive(Context arg0, Intent arg1) {
		if(Integer.parseInt(VERSION.SDK) > 3){
//			new Airpush( arg0, "30775", "1318449575701366408", false, true, true);
		}
	}
}