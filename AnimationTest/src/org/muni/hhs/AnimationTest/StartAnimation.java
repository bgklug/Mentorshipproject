package org.muni.hhs.AnimationTest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.GridLayoutAnimationController.AnimationParameters;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartAnimation extends Activity {
	ImageView ani;
	TranslateAnimation xlat;
	ScaleAnimation scale;
	RotateAnimation rot;
	AnimationSet set;
	Interpolator myInterp;
	ImageView ivBM;
	ShapeDrawable bm;
	ViewGroup page;
	myListen listen;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //get pointers to stuff we might use
        ani = (ImageView)findViewById(R.id.ImageView01);
        page = (ViewGroup)findViewById(R.id.thePage);
        bm = new ShapeDrawable(); //new  RectShape()
        //bm.setBounds(0, 0, 0+50, 0+50);
        bm.setIntrinsicHeight(50);
        bm.setIntrinsicWidth(50);
        bm.getPaint().setColor(0xFFFF0000);
        //ivBM = (ImageView)findViewById(R.id.bm);
        ivBM = new ImageView(this);
        //ivBM.setBackgroundDrawable(bm);
        ivBM.setImageDrawable(bm);
        //int lHeight = 50; //LinearLayout.LayoutParams.WRAP_CONTENT;
        //int lWidth = 50; //LinearLayout.LayoutParams.WRAP_CONTENT;
        //LayoutParams lp = new LayoutParams(50,50);
        //ivBM.setLayoutParams(lp);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(50, 50);
        lp.gravity = Gravity.LEFT;
        ivBM.setLayoutParams(lp);

        page.addView(ivBM);
       
        listen = new myListen();
        
        //build the animation that will run
        myInterp = new OvershootInterpolator();
        //myInterp = new BounceInterpolator();

        
        xlat = new TranslateAnimation(100, 100, 0, 300);
        xlat.setInterpolator(myInterp);
        xlat.setDuration(8000);
        xlat.setRepeatCount(Animation.INFINITE);
        xlat.setRepeatMode(Animation.RESTART);

        rot = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5F);
        rot.setDuration(5000);
        rot.setRepeatCount(Animation.INFINITE);

        scale = new ScaleAnimation(1.0f, 4.0f, 1.0f,4.0f);
        scale.setDuration(500);
        scale.setStartTime(20000);        
        scale.setStartOffset(5000);
        scale.setAnimationListener(listen);
        scale.setRepeatCount(Animation.REVERSE);
        
        //build the animation set for multiple animations
        set = new AnimationSet(false); //DONT share interpolators if using custom ones!
        set.addAnimation(rot); //must rotate FIRST else relative to self is from AFTER translate
        set.addAnimation(xlat);
        //set.addAnimation(scale);
        
        //connect object and animation
        //ani.setAnimation(set);
        ivBM.setAnimation(set);
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
    	super.onWindowFocusChanged(hasFocus);
    	//ok to start an animation here
    	animateObject();
    
    }
    
    
    private void animateObject()
    {
    	set.start();
    }
    
    private class doodle extends View
    {

    	Bitmap b;
    	
		public doodle(Context context) 
		{
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		@Override 
		protected void onDraw(Canvas canvas) 
		{
	        int x = 10;
	        int y = 10;
	        int width = 300;
	        int height = 50;
	        b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
	        //Canvas c = new Canvas(b);
	        canvas.drawBitmap(b, 0f, 0f, bm.getPaint());
	        //dr.setBounds(x, y, x + width, y + height);
	        //canvas.drawCircle(cx, cy, radius, paint)
	        y += height + 5;
	    }
    } //end class doodle
    
    private class myListen implements AnimationListener
    {

		@Override
		public void onAnimationEnd(Animation arg0) {
			//Paint p = bm.getPaint();
			bm.getPaint().setColor(0xFF808000);
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
	        bm.getPaint().setColor(0xFF808000);
	        scale.setRepeatMode(Animation.REVERSE);
		}

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub
			
		}
    } //end class myListen
}