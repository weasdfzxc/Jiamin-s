package com.marauder;

import java.util.Enumeration;
import java.util.Hashtable;

import android.content.res.Resources;
import android.view.View;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;

public class OverlaySets 
{
	//Different kinds of points to mark each place
	public Hashtable<String, ItemizedOverlay<OverlayItem>> points;
	//Pops for showing the information of each place
	public PopItemOverlay pops;
	
	public OverlaySets(Main_Activity main, Resources res, MapView mapview, View pop, View popup)
	{
        points = new Hashtable<String, ItemizedOverlay<OverlayItem>>();
        points.put("科普教育基地", new ItemizedOverlay<OverlayItem>(res.getDrawable(R.drawable.point), mapview));
        points.put("免费", new ItemizedOverlay<OverlayItem>(res.getDrawable(R.drawable.point2), mapview));
        points.put("未知", new ItemizedOverlay<OverlayItem>(res.getDrawable(R.drawable.point4), mapview));
        points.put("公民科学素质教育示范单位", new ItemizedOverlay<OverlayItem>(res.getDrawable(R.drawable.point3), mapview));
        points.put("科普示范社区", new ItemizedOverlay<OverlayItem>(res.getDrawable(R.drawable.point5), mapview));
        points.put("科普示范县(市、区)", new ItemizedOverlay<OverlayItem>(res.getDrawable(R.drawable.point6), mapview));
        pops = new PopItemOverlay(main, null, mapview, pop, popup);
        Enumeration<ItemizedOverlay<OverlayItem>> temp = points.elements();
		while (temp.hasMoreElements())
			mapview.getOverlays().add(temp.nextElement());
		mapview.getOverlays().add(pops);
	}
}
