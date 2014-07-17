package com.j1s.games.trapthefrog;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class CircleTheFrog extends Activity {
	public static Context ctx;
	public int SCREEN_WIDTH;
	public int SCREEN_HEIGHT;
	public int NO_OF_STONES;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        Display display = getWindowManager().getDefaultDisplay(); 
        SCREEN_WIDTH  = display.getWidth();
        SCREEN_HEIGHT = display.getHeight();
        NO_OF_STONES  = StoneRow.WITHOUT_HALF_STONE_COUNT;
		setContentView(R.layout.circlethefroglayout);

		RelativeLayout outerLayout = (RelativeLayout) findViewById(R.id.frameLayout);
		//tableLayout.setStretchAllColumns(true); 
		outerLayout.addView(getRowWithoutHalfStone(1));
		outerLayout.addView(getRowWithHalfStone(2));
		outerLayout.addView(getRowWithoutHalfStone(3));
		outerLayout.addView(getRowWithHalfStone(4));
		outerLayout.addView(getRowWithoutHalfStone(5));
		outerLayout.addView(getRowWithHalfStone(6));
		outerLayout.addView(getRowWithoutHalfStone(7));
		outerLayout.addView(getRowWithHalfStone(8));
		outerLayout.addView(getRowWithoutHalfStone(9));
		
		StoneView stone = (StoneView) findViewById(55);
		stone.setBackgroundResource(R.drawable.smallstonerightfrog);
	
	}
	
	public LinearLayout getRowWithoutHalfStone(int rowId){
		StoneRow row = new StoneRow(StoneRow.WITHOUT_HALF_STONE, ctx);
		row.setId(rowId);
		for(int i=1; i<=StoneRow.WITHOUT_HALF_STONE_COUNT; i++){
			StoneView stoneView = new StoneView((rowId*10)+i, StoneView.FULL_STONE, ctx, SCREEN_HEIGHT/NO_OF_STONES, SCREEN_HEIGHT/NO_OF_STONES);
			row.addView(stoneView);
		}
		return row;
	}
	
	public LinearLayout getRowWithHalfStone(int rowId){
		StoneRow row = new StoneRow(StoneRow.WITH_HALF_STONE, ctx);
		row.setId(rowId);
		for(int i=1; i<=StoneRow.WITH_HALF_STONE_COUNT; i++){
			StoneView stoneView;
			if(i==1)
				stoneView = new StoneView((rowId*10)+i, StoneView.LEFT_HALF_STONE, ctx, SCREEN_HEIGHT/NO_OF_STONES, SCREEN_HEIGHT/NO_OF_STONES);
			else if(i == StoneRow.WITH_HALF_STONE_COUNT)
				stoneView = new StoneView((rowId*10)+i, StoneView.RIGHT_HALF_STONE, ctx, SCREEN_HEIGHT/NO_OF_STONES, SCREEN_HEIGHT/NO_OF_STONES);
			else
				stoneView = new StoneView((rowId*10)+i, StoneView.FULL_STONE, ctx, SCREEN_HEIGHT/NO_OF_STONES, SCREEN_HEIGHT/NO_OF_STONES);
			row.addView(stoneView);
		}
		return row;
	}

}