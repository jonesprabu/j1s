package com.domain.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class LoginRegisterActivity extends Activity {

    private static final String TAG = "LoginRegisterActivity";
    ViewFlipper mViewFlipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
        mViewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);
        mViewFlipper.setInAnimation(this, R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(this, R.anim.slide_out_right);
        mViewFlipper.setFlipInterval(2000);
        mViewFlipper.startFlipping();
	}

    public void login(View v){

        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    public void register(View v){

        Intent registerIntent = new Intent(this, RegisterActivity.class);
        startActivity(registerIntent);
    }



}
