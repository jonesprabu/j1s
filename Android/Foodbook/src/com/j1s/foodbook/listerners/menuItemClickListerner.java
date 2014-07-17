package com.j1s.foodbook.listerners;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;


public class menuItemClickListerner implements AdapterView.OnItemClickListener{
	
	Context context;
	public menuItemClickListerner(Context context) {
		this.context = context;
	}

	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		
		Toast.makeText(context, "Pos - "+pos+" -- Id - "+id, Toast.LENGTH_SHORT).show();
		
	}

}
