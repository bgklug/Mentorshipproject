package org.muni.newfuntest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.graphics.drawable.ShapeDrawable;
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

public class Activitytest extends Activity {
	
	ViewGroup pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainone);
        pic = (ViewGroup)findViewById(R.id.thePage);
        for(int i=0; i<5; i++)
        	pic.addView(newGraphic(0,0));
    }
    
    private ImageView newGraphic(int x,int y)
    {
    	ImageView face;
    	ShapeDrawable bm;
        bm = new ShapeDrawable(); //new  RectShape()
        //bm.setBounds(0, 0, 0+50, 0+50);
        bm.setIntrinsicHeight(50);
        bm.setIntrinsicWidth(50);
        bm.getPaint().setColor(0xFFFF0000);
        face = new ImageView(this);
        face.setImageDrawable(bm);
        return face;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainone, menu);
        return true;
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
    	super.onWindowFocusChanged(hasFocus);
    	//ok to start an animation here
    
    }
}
