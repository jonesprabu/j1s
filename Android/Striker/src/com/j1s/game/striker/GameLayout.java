package com.j1s.game.striker;




import android.content.Context;
import android.widget.RelativeLayout;

@SuppressWarnings("deprecation")
public class GameLayout extends RelativeLayout {

	public GameLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		super.setBackgroundResource(R.drawable.background_image);
        super.setLayoutParams( new LayoutParams(LayoutParams.MATCH_PARENT
        							, LayoutParams.MATCH_PARENT));
        super.setPadding(25, 25, 25, 25);
        
	}

}
