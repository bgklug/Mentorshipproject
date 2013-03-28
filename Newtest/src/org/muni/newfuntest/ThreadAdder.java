package org.muni.newfuntest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ThreadAdder extends Thread {
	public int id;
	public String url;
	public int[]results;
	
	public void run(){
		results = XMLpull(id,url);
	}
	public int[] XMLpull(int categoryID, String urlstring)
    {
    	StringBuilder sb = new StringBuilder();
    	List<Integer> al = new ArrayList();
    	boolean success;
    	success = false;
    	boolean working;
    	working = true;
    	int vals[];
    	int result;
    	    	
    	try
    	{
    		URL url = new URL(urlstring + Integer.toString(categoryID));
    		InputStream in = url.openConnection().getInputStream();
	    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	    	XmlPullParser parser = factory.newPullParser();
	    	parser.setInput(in, null);
	    	int eventtype = parser.next();
	    	boolean done = (eventtype==XmlPullParser.END_DOCUMENT);
	    	boolean wantText=false;
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
	    	success = true;
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
    	
    }
}
