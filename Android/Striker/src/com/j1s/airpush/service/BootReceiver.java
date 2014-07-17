package com.j1s.airpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public class BootReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		if(Integer.parseInt(VERSION.SDK) > 3){
			//new Airpush(arg0,"65421","1318449575701366408", true, true, true);
		}
	}
}
