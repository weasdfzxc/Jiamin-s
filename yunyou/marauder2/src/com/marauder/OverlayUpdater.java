package com.marauder;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class OverlayUpdater
{
	//The reference of information overlay sets
    OverlaySets overlays = null;
    //Thread for updating
    HttpThread kplocation = null;
    Runnable kpinformation = null;
    Thread update = null;
    //location data
    JSONArray locations = null;
    
    public OverlayUpdater(final Main_Activity main, final MapView mapview, Context context, OverlaySets sets) 
    {
        this.overlays = sets;
        //Create a thread to get the location of all place
        kplocation = new HttpThread();
        kplocation.setUrl(context.getString(R.string.main_url) + "kplocation");
        kplocation.setData(null);
        kplocation.setHandler(new Handler() 
        {
            public void handleMessage(Message message)
            {
                try
                {
                	//Clear old overlays
                	Enumeration<ItemizedOverlay<OverlayItem>> temp = overlays.points.elements();
                    while (temp.hasMoreElements())
                    	temp.nextElement().removeAll();
                    //Get location 
                    JSONObject responseJson = new JSONObject(message.obj.toString());
                    locations = responseJson.getJSONArray("locations");
                    //Create several lists of overlays 
                    Hashtable<String, List<OverlayItem>> pointitems = new Hashtable<String, List<OverlayItem>>();
                    Enumeration<String> temp2 = overlays.points.keys();
                    while (temp2.hasMoreElements())
                    	pointitems.put(temp2.nextElement(), new LinkedList<OverlayItem>());
                    //Add each item
                    for (int i = 0; i < locations.length(); ++i)
                    {
                    	String name = locations.getJSONObject(i).getString("name");
                    	String type = locations.getJSONObject(i).getString("type");
                        String locationX = locations.getJSONObject(i).getString("locationX");
                        if (locationX == "null") locationX = null;
                        String locationY = locations.getJSONObject(i).getString("locationY");
                        if (locationY == "null") locationY = null;
                        //Create OverlayItem
                        if (locationX != null && locationY != null)
                        {
                        	GeoPoint geoPoint = new GeoPoint((int)(Double.valueOf(locationY) * 1e6), 
                        			(int)(Double.valueOf(locationX) * 1e6));
                        	OverlayItem pover = new OverlayItem(geoPoint, name, "kplocation");
                        	pover.setAnchor(OverlayItem.ALING_CENTER);
                        	List<OverlayItem> list = pointitems.get(type);
                        	if (list != null) 
                        		list.add(pover);
                        }
                    }
                    //Add all items to the sets of overlay
                    temp2 = overlays.points.keys();
                    while (temp2.hasMoreElements())
                    {
                    	String key = temp2.nextElement();
                    	overlays.points.get(key).addItem(pointitems.get(key));
                    }
                    //Refresh map view
                    mapview.refresh();
                    
                    main.CloseNotify(Main_Activity.NOTIFY_TIP);
                }
                catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (NullPointerException e)
                {
                }
            }
        });
        kpinformation = new Runnable()
        {
			@Override
			public void run() 
			{
				if (locations != null)
					try
					{
						main.SetNotify(Main_Activity.NOTIFY_TIP, "正在加载中...");
						//Clear old overlays
						overlays.pops.removeAll();
						for (int i = 0; i < locations.length(); ++i)
	                    {
							String name = locations.getJSONObject(i).getString("name");
	                    	String type = locations.getJSONObject(i).getString("type");
	                        String locationX = locations.getJSONObject(i).getString("locationX");
	                        if (locationX == "null") locationX = null;
	                        String locationY = locations.getJSONObject(i).getString("locationY");
	                        if (locationY == "null") locationY = null;
	                        //Create OverlayItem
	                        if (locationX != null && locationY != null)
	                        {
	                        	GeoPoint geoPoint = new GeoPoint((int)(Double.valueOf(locationY) * 1E6), 
	                        			(int)(Double.valueOf(locationX) * 1E6));
	                        	OverlayItem popover = new OverlayItem(geoPoint, name, type);
	                        	popover.setAnchor(0.5f, 1.0f);
	                        	popover.setMarker(GetPopDrawable(overlays.pops.pop, name));
	                        	overlays.pops.addItem(popover);
	                        }
	                    }
						//Refresh map view
	                    mapview.refresh();
	                    
	                    main.CloseNotify(Main_Activity.NOTIFY_TIP);
					}
					catch (JSONException e)
	                {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
			}
        };
        Log.d("debug", "Thread in FixedPositions created.");
    }
    
    public void UpdateKpLocation()
    {
        Thread thread = new Thread(kplocation);
        thread.start();
    }
    
    public void ShowPop()
    {
    	update = new Thread(kpinformation);
    	update.start();
    }
    
    public void HidePop()
    {
    	if (update != null)
    		update.interrupt();
    	overlays.pops.removeAll();
    }
    
    public void HidePopup()
    {
    	overlays.pops.HidePopup();
    }
    
	Drawable GetPopDrawable(View layout, String title)
	{         
        Button text = (Button)layout.findViewById(R.id.title);
        text.setText(title.toCharArray(),0,title.length());
        Bitmap snapshot = convertViewToBitmap(layout);
        Drawable drawable = (Drawable)new BitmapDrawable(snapshot);
        return drawable;
    }
	
	Bitmap convertViewToBitmap(View view) 
	{
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());  //根据字符串的长度显示view的宽度
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}
}
