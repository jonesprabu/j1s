package com.j1s.games.trapthefrog;



/*public class StoneView extends ImageView {
	
	public static final int FULL_STONE = 1;
	public static final int LEFT_HALF_STONE = 2;
	public static final int RIGHT_HALF_STONE = 3;
	private boolean isDrowned;
	private MediaPlayer mp = MediaPlayer.create(CircleTheFrog.ctx, R.raw.splash);
	
	public StoneView(int id, int stoneType, Context context, int width, int height) {	
		super(context);

		//this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.setAdjustViewBounds(true);
		this.setMaxHeight(height);
		this.setMaxWidth(width);
		this.setScaleType(ImageView.ScaleType.FIT_CENTER);
		this.setId(id);		
		this.isDrowned = false;
		if(stoneType == StoneView.FULL_STONE){
			this.setBackgroundResource(R.drawable.smallstone);
		}else if(stoneType == StoneView.LEFT_HALF_STONE){
			this.setBackgroundResource(R.drawable.smallstoneleft);
		}else if(stoneType == StoneView.RIGHT_HALF_STONE){
			this.setBackgroundResource(R.drawable.smallstoneright);
		}
		
		super.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				final StoneView stone = (StoneView) v;
				
				if(stone.isDrowned != true){

					stone.mp.start();
					
					Handler delayHandler =  new Handler();
					delayHandler.postDelayed(new Runnable() {
						
						public void run() {
							
							stone.mp.release();
							stone.mp = null;
						}
					}, 500);
					
					System.out.println("Stone width"+stone.getWidth());
					
					int id = stone.getId();
					int i = id/10;
					if(i == 1 && i%2 == 0)
						stone.setBackgroundResource(R.drawable.splashsmall);
					else
						stone.setBackgroundResource(R.drawable.splash);
	
					AnimationDrawable splashAnimation = (AnimationDrawable) stone.getBackground();
					splashAnimation.start();
					stone.isDrowned = true;
					
				}
				
				
				return false;
			}
		});
	}
	*/

public class StoneView{
	public static void main(String arg[]){
		String str1 = "Abhishek";
		String str2 = "Aishwarya";
		String flames = "FLAMES";
		
		int count = str1.length() + str2.length();
		
		System.out.println(count);
		
		for(int i=0; i<str1.length(); i++){
			for(int j=0; j<str2.length(); j++ ){
				if(str1.charAt(i) == str2.charAt(j)){
					count=count-2;
					break;
				}
			}			
		}
		
		
		System.out.println("Count -- "+count);
		/*System.out.println(flames.charAt(count%6));
		
		for(int i=0; i<count; i++){
			
		}*/
	}
	
	
	
	
	
}
