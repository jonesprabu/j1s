package com.j1s.foodbook;

import java.util.ArrayList;
import java.util.Arrays;

import com.j1s.foodbook.listerners.menuItemClickListerner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodBookHomeActivity extends Activity {
	
	
	private ListView menuListView ;  
	private ArrayAdapter<String> listAdapter ; 
	  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.main);
        
        menuListView = (ListView) findViewById(R.id.menuListView);
        // Create and populate a List of planet names.  
        String[] menu = new String[] { "MENU" };    
        ArrayList<String> menuList = new ArrayList<String>();  
        menuList.addAll( Arrays.asList(menu) );  
          
        // Create ArrayAdapter using the planet list.  
        listAdapter = new ArrayAdapter<String>(this, R.layout.menuitem, menuList);  
          
        // Add more planets. If you passed a String[] instead of a List<String>   
        // into the ArrayAdapter constructor, you must not add more items.   
        // Otherwise an exception will occur.
        
        listAdapter.add( "Soups" );  
        listAdapter.add( "Starters" );  
        listAdapter.add( "Chicken" );  
        listAdapter.add( "Lamb Special" );  
        listAdapter.add( "Sea Food" );  
        listAdapter.add( "Fish" );  
        listAdapter.add( "Rice Bowl - Dum Biryani" );  
        listAdapter.add( "Rabbit" );  
        listAdapter.add( "Set Meals" );  
        listAdapter.add( "Rice and Noodles" );  
        listAdapter.add( "Papad and Salad" );  
        listAdapter.add( "Egg" );  
        listAdapter.add( "Tandoori Claypot" );  
        listAdapter.add( "Vegetarian" );  
        listAdapter.add( "Thava Special" );  
        listAdapter.add( "Dosa Corner" );  
        listAdapter.add( "Steam Zone" );  
        listAdapter.add( "Steam Zone" );  
        listAdapter.add( "Fresh Juices" );  
        listAdapter.add( "Ice Cream" );  
        listAdapter.add( "Ethnic Delights" );  
        
        // Set the ArrayAdapter as the ListView's adapter.  
        menuListView.setAdapter( listAdapter );
        
        menuListView.setOnItemClickListener(new menuItemClickListerner(this));
        
    }
}