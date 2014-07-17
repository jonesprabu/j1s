package com.j1s.game.striker;

import android.content.Context;
import android.widget.TableLayout;

public class GameTabelLayout extends TableLayout {

	public GameTabelLayout(Context context) {
		super(context);
		super.setStretchAllColumns(true);
        super.setLayoutParams( 
        		new LayoutParams(LayoutParams.MATCH_PARENT
        				, LayoutParams.MATCH_PARENT));
	}

}
