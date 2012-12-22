package muni.org;

//hi
//back at you
import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Animate1Activity extends Activity {
	ImageView ivBM;
	ShapeDrawable bm;
	ViewGroup page;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        //get pointers to stuff we might use
        page = (ViewGroup)findViewById(R.id.thePage);

        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(50, 50);
        //lp.gravity = Gravity.LEFT;
        //ivBM.setLayoutParams(lp);

        for (int i=0; i<5; i++)
        	page.addView(newGraphic(0,0));
       
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
    	super.onWindowFocusChanged(hasFocus);
    	//ok to start an animation here
    
    }
    
    private ImageView newGraphic(int x,int y)
    {
    	ImageView ivBM;
    	ShapeDrawable bm;
        bm = new ShapeDrawable(); //new  RectShape()
        //bm.setBounds(0, 0, 0+50, 0+50);
        bm.setIntrinsicHeight(50);
        bm.setIntrinsicWidth(25);
        bm.getPaint().setColor(0xFFFF0000);
        ivBM = new ImageView(this);
        ivBM.setImageDrawable(bm);
        return ivBM;
    }
    
}