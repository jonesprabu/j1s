package com.j1s.games.followme;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private static int clickCounts;
    public static ImageView imageViews[];
    
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 9;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        
    	SoundButton soundButton;
        /*WindowManager mWinMgr = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int screenWIDTH  = mWinMgr.getDefaultDisplay().getWidth();
        int imageWidth  = screenWIDTH/3;
        int imageHeight = imageWidth; */
        
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	soundButton = new SoundButton(mContext, position);
            /*imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageHeight));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);*/
            //imageView.setPadding(8, 8, 8, 8);
            /*Log.d("jones", "setId >>>>>>>>>> "+position);
            imageView.setId(position);*/
        } else {
        	soundButton = (SoundButton) convertView;
        }

        /*imageView.setImageResource(R.drawable.button);
        imageView.setOnTouchListener(new OnTouchListener() {
			
			
			public boolean onTouch(View v, MotionEvent event) {
				ImageView imgView = (ImageView) v;
				int index = imgView.getId();
				if(event.getAction() == MotionEvent.ACTION_DOWN){					
					if(Home.path.get(clickCounts)==index){
						SoundManager.playSound(imgView, index);//playSound(imgView, index);							
					//}
					return true;
				}else if(event.getAction() == MotionEvent.ACTION_UP){					
					if(Home.path.get(clickCounts)!=index){
						final Dialog dialog = new Dialog(Home.context);
				        dialog.setContentView(R.layout.dialoglayout);
				        dialog.setTitle("Its Wrong... Try again");
				        dialog.setCancelable(true);
				        View startBut = dialog.findViewById(R.id.startbut);
				        startBut.setOnTouchListener(new OnTouchListener() {
							
							
							public boolean onTouch(View v, MotionEvent event) {
								if(event.getAction()==MotionEvent.ACTION_DOWN){
									System.out.println("In On Touch------");
									Log.d("jones", "In On Touch----");
									clickCounts=0;
									new Home().startPlay(Home.levels);			
									dialog.dismiss();									
									return true;
								}
								return false;
							}
						});
				        dialog.show();
					}else{
						SoundManager.stopSound(imgView, index);
						clickCounts++;
						if(clickCounts==Home.path.size()){
							Home.levels++;
							clickCounts=0;
							new Home().startPlay(Home.levels);
						}
					}
					
					return true;
				}
				return false;
				
			}
		});*/
        
        //imageViews[position] = imageView;
        
        return soundButton;
    }

}