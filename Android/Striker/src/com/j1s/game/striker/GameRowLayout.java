package com.j1s.game.striker;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableRow;

public class GameRowLayout extends TableRow {

	public GameRowLayout(Context context) {
		super(context);
		//super.setGravity(Gravity.CENTER_HORIZONTAL);
    	super.setLayoutParams( 
        		new LayoutParams(LayoutParams.MATCH_PARENT
        				, LayoutParams.MATCH_PARENT));
	}

}
