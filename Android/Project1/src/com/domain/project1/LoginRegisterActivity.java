package com.domain.project1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class LoginRegisterActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);
		viewFlipper.setInAnimation(this, R.anim.abc_slide_in_top);
		viewFlipper.setOutAnimation(this, R.anim.abc_slide_out_bottom);
		viewFlipper.setFlipInterval(2000);
		viewFlipper.startFlipping();
	}

}
