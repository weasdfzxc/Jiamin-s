package com.marauder;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class PopItemOverlay extends ItemizedOverlay<OverlayItem>
{
	//Activity "Main"
	Main_Activity main;
	//BaiduMap view
	MapView map;
	//Use for showing more information and buttons
	public View pop;
	public View popup;
	PopupOverlay popupoverlay;
	//Temporarily saving the onTap item
	OverlayItem ontapitem;
	
	public PopItemOverlay(Main_Activity main, Drawable mark, MapView view, View pop, View popup) 
	{
		super(mark, view);
		this.main = main;
		this.map = view;
		this.pop = pop;
		this.popup = popup;
		popupoverlay = new PopupOverlay(map, new PopupClickListener() 
		{                  
	        @Override  
	        public void onClickedPopup(int index)
	        {  
	        }
	    });
	}
	
	protected boolean onTap(int index) 
	{  
        //There process the event of item on tap
		ontapitem = getItem(index);
		//Set the button for pathing
		Button path = (Button)popup.findViewById(R.id.path);
		path.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				popupoverlay.hidePop();
				//Ready for opening Route_Activity
				GeoPoint start = main.GetMyLocation();
				GeoPoint end = ontapitem.getPoint();
				Intent routedata = new Intent(main, Route_Activity.class);
				routedata.putExtra("city", main.city);
				routedata.putExtra("zoom", main.lastzoom);
				routedata.putExtra("centerLA", main.lastpos.getLatitudeE6());
				routedata.putExtra("centerLO", main.lastpos.getLongitudeE6());
				routedata.putExtra("startLA", start.getLatitudeE6());
				routedata.putExtra("startLO", start.getLongitudeE6());
				routedata.putExtra("endLA", end.getLatitudeE6());
				routedata.putExtra("endLO", end.getLongitudeE6());
				main.startActivity(routedata);
			}
		});
		Button activity = (Button)popup.findViewById(R.id.activity);
		activity.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				popupoverlay.hidePop();
				//Ready for opening Message_Activity
				Intent intent = new Intent(main, Message_Activity.class);
				intent.putExtra("name", ontapitem.getTitle());
				main.startActivity(intent);
			}
		});
		//Show the place's information
		String temp = ontapitem.getTitle();
		Button title = (Button)popup.findViewById(R.id.title);
        title.setText(temp.toCharArray(), 0, temp.length());
        TextView type = (TextView)popup.findViewById(R.id.type);
        temp = ontapitem.getSnippet();
        type.setText(temp.toCharArray(), 0, temp.length());
    	popupoverlay.showPopup(popup, ontapitem.getPoint(), 0);
        return true;  
    }  
	
    public boolean onTap(GeoPoint pt, MapView mapView)
    {  
        //There process the event of map on tap
        super.onTap(pt,mapView); 
        popupoverlay.hidePop();
        return false;  
    }
    
    public void HidePopup()
    {
    	popupoverlay.hidePop();
    }
}
