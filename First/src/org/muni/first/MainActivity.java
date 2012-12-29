package org.muni.first;

import android.view.animation.Animation;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.view.Menu;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Interpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation;
import android.animation.Animator.AnimatorListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.ViewGroup;



public class MainActivity extends Activity {
	
	ImageView smile, frown;
	ShapeDrawable ani;
	Bitmap bit;
	Canvas screen;
	ViewGroup pic;
	TextView move;
	TranslateAnimation down, up;
	Interpolator myinterp;
	ScaleAnimation big;
	AnimationSet group;
	AnimatorListener end;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        pic = (ViewGroup)findViewById(R.drawable.positive_smiley_happy);
        for(int i=0; i<5; i++)
        	pic.addView(pic,0,0);
    }
    
    private ImageView newGraphic(int x,int y)
    {
		return null;
        
       // bit.createBitmap(,20,20,50,50);
       // screen = Canvas(bit);
        //ani = new ShapeDrawable();
       // ani.setIntrinsicHeight(50);
       // ani.setIntrinsicWidth(50);
       // ani.getPaint().setColor(0xFFFF0000);
      //  smile = new ImageView(this);
      //  smile.setImageDrawable(ani);
        
        
        
        
       // smile = (ImageView)findViewById(R.id.imageView02);
       // move = (TextView)findViewById(R.id.textView2);
      // myinterp = new BounceInterpolator();
       // myinterp = new OvershootInterpolator(8.0f);
       // myinterp = new AnticipateInterpolator(5f);
        
        //group= new AnimationSet(false);
       // down = new TranslateAnimation(100, 100, 200, 400);
       // down.setInterpolator(myinterp);
       // down.setDuration(5000);
       // down.setStartOffset(1000);
       // down.setRepeatCount(Animation.INFINITE);
       // down.setRepeatMode(Animation.RESTART);
       // group.addAnimation(down);
        
       // big = new ScaleAnimation(.5f,2,1,1);
        //big.setDuration(5000);
        
       // big.setRepeatCount(Animation.INFINITE);
       // big.setRepeatMode(Animation.RESTART);
        //group.addAnimation(big);
       
        
       // smile.setAnimation(group);
       // smile.setAnimation(down);
        //pic.setAnimation(down);
        
        //frown = (ImageView)findViewById(R.id.ImageView01);
        //up = new TranslateAnimation(100, 100, 400, 200);
        //up.setInterpolator(myinterp);
        //up.setDuration(5000);
        //up.setStartOffset(2000);
        //frown.setAnimation(up);
        
    }
  //  private Canvas Canvas(Bitmap bit2) {
		// TODO Auto-generated method stub
		//return null;
	//}
	@Override
    public void onWindowFocusChanged(boolean hasFocus){
    	super.onWindowFocusChanged(hasFocus);
    	//animateObject();
    }
    
    private void animateObject(){
    	//group.start();
    	down.start();
    	//up.start();
    }

   // @Override
    //public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_main, menu);
      //  return true;
   // }
    
}
