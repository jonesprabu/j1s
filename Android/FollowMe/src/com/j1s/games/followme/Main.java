package com.j1s.games.followme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.*;

public class Main extends Activity implements OnClickListener {

	public Context thisCtx;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);
		// Set up click listeners for all the buttons
		View newButton = findViewById(R.id.start_button);
		newButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_button:
			Intent start = new Intent(this, Home.class);
			startActivity(start);
			break;
		case R.id.about_button:
			Intent abt = new Intent(this, About.class);
			startActivity(abt);
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}

}