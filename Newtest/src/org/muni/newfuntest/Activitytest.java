package org.muni.newfuntest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
//import android.widget.LinearLayout;

import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Interpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation;
import android.view.ViewGroup;
import android.view.View;

import android.animation.Animator.AnimatorListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.Color;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;
import java.util.List;
import android.view.GestureDetector;
//import android.view.ViewGroup.LayoutParams;
import android.view.MotionEvent;

public class Activitytest extends Activity {
	
	TextView tv;
	ViewGroup pic;
	View pics;
	Interpolator myinterp;
	TranslateAnimation down;
	ImageView box;
	GestureDetector finger;
	ThreadAdder XMLpullx = new ThreadAdder();
	ThreadAdder XMLpully = new ThreadAdder();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        tv = (TextView)findViewById(R.id.tv); //isn't working
        
//        tv.setText("Hello, Android world");       
        
        
        XMLpullx.id = 3;
        XMLpullx.url = getString(R.string.url_GetSampleSet);
        XMLpullx.start();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        setContentView(R.layout.vertical_tab);
        pic = (ViewGroup)findViewById(R.id.theView);
                
        finger = new GestureDetector(this, new myGesture());
        
//        myinterp = new BounceInterpolator();
        myinterp = new OvershootInterpolator(1.0f);
//        myinterp = new AnticipateInterpolator(5f);
        
        int min = 0;//For range of value of the vectors
        int max = 1;
        
//       int xvalfirst[] = {10,70,110,256,500};//initial test code, before ThreadAdder
//       int xvallast[] = {15, 70, 200, 150, 650};
//       int yvalfirst[] = {50, 10, 200, 400, 100};
//       int yvallast[] = {150, 50, 400, 200, 100};
        
       int leg = XMLpullx.results.length;
       int inc[] = new int[leg];
       int v = 1;
       int a =0;
       for(a=0; a<leg; a++){
    	   inc[a]= v;
    	   v++;
       }
//       float xval[] = display(xvallast);
//       float yval[] = ydisplay(yvallast);
       
       float yval[] = ydisplay(XMLpullx.results);
       float xval[] = display(inc);
		for(int i=0; i<leg; i++){ //make new method for this
			box = newGraphic();
			pic.addView(box);
//			pics.addView(box);
			down = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, xval[i], Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, yval[i]);//start bottom left corner
			//down = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, xval[i], Animation.RELATIVE_TO_PARENT, xval[i], Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, yval[i]);//start line bottom
			down.setDuration(1000);
			down.setFillAfter(true); //makes the points stay after animation
		    down.setInterpolator(myinterp);//doesn't really work. the points fall off screen
			/**down = new TranslateAnimation(xvalfirst[i], xvallast[i], yvalfirst[i], yvallast[i]);
		    down.setInterpolator(myinterp);
		    down.setDuration(5000);
		   // down.setStartOffset(1000);
		    //down.setRepeatCount(Animation.INFINITE);
		    //down.setRepeatMode(Animation.RESTART);*/
		    //pic.setAnimation(down);
		    box.setAnimation(down);
		    
	
		}
			
    }
    
    public float[] display(int x []){
    	
    	int maxval = 0;
    	float endval[] = new float[x.length];
    	
    	for(int i=0; i< x.length; i++){
    		if (maxval < x[i])
    			maxval = x[i];    		
    	}
    	maxval = (int) (maxval *1.1);
    	for(int i=0; i< x.length; i++){
    		endval[i] = (float)x[i]/maxval;
    	}
		return endval;
    	 
    }
    public float[] ydisplay(int x []){
    	
    	int maxval = 0;
    	float endval[] = new float[x.length];
    	
    	for(int i=0; i< x.length; i++){
    		if (maxval < x[i])
    			maxval = x[i];    		
    	}
    	maxval = (int) (maxval *1.2);
    	for(int i=0; i< x.length; i++){
    		endval[i] = 1-((float)x[i]/maxval);
    	}
		return endval;
		}
    
    private int color(int mag, int min, int max){
    	int grayval;
    	int lilco = mag/(max - min) * 255;
    	if (lilco > 255){
    		grayval = Color.rgb(255, 255, 255);
    	}else if(lilco < 0){
    		grayval = Color.rgb(0, 0, 0);
    	}else{
    		grayval = Color.rgb(lilco, lilco, lilco);
    	}
    	return grayval;
    }
    
    private ImageView newGraphic()
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
    
    public boolean onTouchEvent(MotionEvent event)
    {

    	//pass touch event to gesture detector
    	return finger.onTouchEvent(event);
    }

    /*private void doFling()
    {
    	//switchActivity();
		tv.setText("Doing a fling...");
    	//getCategoryList();
    	XMLpull(9);
    }*/

   /* private int[] XMLpull(int categoryID)
    {
    	StringBuilder sb = new StringBuilder();
    	List<Integer> al = new ArrayList();
    	boolean success;
    	success = false;
    	int vals[];
    	int result;
    	    	
    	try
    	{
//    		DefaultHttpClient client = new DefaultHttpClient();
//    		HttpUriRequest request = new HttpGet(getString(R.string.url_GetSampleSet) + Integer.toString(categoryID));
//    		HttpResponse response = client.execute(request);
//    		InputStream in = response.getEntity().getContent();
    		URL url = new URL(getString(R.string.url_GetSampleSet) + Integer.toString(categoryID));
    		InputStream in = url.openConnection().getInputStream();
//	    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//	    	XmlPullParser parser = factory.newPullParser();
//	    	parser.setInput(in, null);
//	    	int eventtype = parser.next();
//	    	boolean done = (eventtype==XmlPullParser.END_DOCUMENT);
//	    	boolean wantText=false;
	    	while (!done)
	    	{
	    		switch (eventtype){
	    		
		    		case XmlPullParser.START_TAG:
		    			if (parser.getName().equals("count"))
		    				wantText=true;
		    			break;
		    		case XmlPullParser.TEXT:
		    			if (wantText)
		    			{
		    				al.add(Integer.parseInt(parser.getText()));
		    				wantText=false;
		    			}
		    			break;
		    		case XmlPullParser.END_TAG:
		    			break;
		    		case XmlPullParser.END_DOCUMENT:
		    			done=true;
		    			break;
	    		}
	    		if (!done) eventtype = parser.next();
	    	}
//	    	tv.setText(sb.toString());
	    	success = false;
	    	result = 0;
    	}
    	catch (MalformedURLException e) 
    	{
    		//tv.setText("URL exception:" + e.getMessage());
    		success = false;
    		result = 50;
		}
    	catch (UnknownServiceException e) 
    	{
    		//tv.setText("URL exception:" + e.getMessage());
    		success = false;
    		result = 250;
		}
 
    	catch (IOException e)
    	{
    		//tv.setText("IO exception:" + e.getMessage());
    		success = false;
    		result = 100;
    	} 
//    	catch (XmlPullParserException e) 
//    	{
    		//tv.setText("Xml pull exception:" + e.getMessage());
//    	   		success = false;
//    	   		result = 150;
//    	}
    	catch (Exception e){
   		success = false;
   		result = 300;
    	}
    
//    	al.toArray(new int[al.size()] vals);// didn't work
    	if(success){
    	int i=0;
    	 vals = new int[al.size()];
    	for(Object item: al){
    		vals[i++] = Integer.parseInt(item.toString());
    	}

    	}
    	else{
    		vals= new int[4];
    		 vals[0]=result;
    		 vals[1]=200;
    		 vals[2]=10;
    		 vals[3]=50;
    		}
//    	int vals[] = {150, 50, 400, 200};
    	return vals;
    	
    }*/

    /*private void getCategoryList()
    {
    	String theURL = getString(R.string.url_GetCategories);
    	StringBuilder sb = new StringBuilder();
    	try {
			URL url = new URL(theURL);
			URLConnection conn = url.openConnection();
			HttpURLConnection http = (HttpURLConnection)conn;
			int responseCode = http.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				//display
				InputStream in = http.getInputStream();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(in);

				Element root = doc.getDocumentElement();
				NodeList list = root.getElementsByTagName("entry");
				sb.append("list size="+Integer.toString(list.getLength()));
				
				for (int i=0; i<list.getLength(); i++)
				{
					Element entry = (Element)list.item(i);
					
					Element content = (Element)entry.getElementsByTagName("content").item(0);
					Element props = (Element)content.getElementsByTagName("m:properties").item(0);
					Element descr = (Element)props.getElementsByTagName("d:categoryDescription").item(0);
					if (descr!=null)
						sb.append(descr.getFirstChild().getNodeValue());
					else
						sb.append("no description ");
					//sb.append(content.getNodeValue());
				}
				tv.setText(sb.toString());
			}
			else
				tv.setText("response code not OK");
		} 
    	catch (MalformedURLException e) {
			//e.printStackTrace();
    		tv.setText("URL exception:" + e.getMessage());
		}
    	catch (IOException e)
    	{
    		tv.setText("IO exception:" + e.getMessage());    	
    	} catch (ParserConfigurationException e) 
    	{
    		tv.setText("XML err:" + e.getMessage());    	
		} catch (SAXException e) 
		{
    		tv.setText("SAX err:" + e.getMessage());    	
		}
    	
    }*/

    private class myGesture extends GestureDetector.SimpleOnGestureListener
    {

    	/*@Override
    	/*public boolean onFling(MotionEvent e1,MotionEvent e2,float vx,float vy)
    	{
    		//make note that we just got a fling!
    		doFling();
    		return true;
    	}*/
    }
}
