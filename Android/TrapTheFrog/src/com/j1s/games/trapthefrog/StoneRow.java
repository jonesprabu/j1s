package com.j1s.games.trapthefrog;

import android.content.Context;
import android.widget.LinearLayout;

public class StoneRow extends LinearLayout{
	
	public static final int WITHOUT_HALF_STONE = 1;
	public static final int WITH_HALF_STONE = 2;
	public static final int WITHOUT_HALF_STONE_COUNT  = 9;
	public static final int WITH_HALF_STONE_COUNT = 10;
	Context ctx;
	
	public StoneRow(int rowType, Context context) {
		super(context);
		super.setOrientation(LinearLayout.HORIZONTAL);
		super.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}
	
}
