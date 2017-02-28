package com.marauder;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.map.TransitOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Route_Activity extends Activity implements OnClickListener
{
	//The basic components
    BMapManager bmap = null;
    MapView mapview = null;
    MapController mapcontrol = null;
    //Use for searching route
    MKSearch mksearch = null;
    String city = "";
    MKPlanNode start = null;
    MKPlanNode end = null;
    int typeid = 0;
    int trafficid = 0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//Get route data from last activity
		Intent data = getIntent();
		city = data.getStringExtra("city");
		GeoPoint center = new GeoPoint(data.getIntExtra("centerLA", Main_Activity.CENTER_LA), 
				data.getIntExtra("centerLO", Main_Activity.CENTER_LO));
		int zoom = data.getIntExtra("zoom", Main_Activity.ZOOM_LEVEL);
		start = new MKPlanNode();
		end = new MKPlanNode();
		start.pt = new GeoPoint(data.getIntExtra("startLA", 0), data.getIntExtra("startLO", 0));
		end.pt = new GeoPoint(data.getIntExtra("endLA", 0), data.getIntExtra("endLO", 0));
		//Initialize
		InitializeBaiduMap(center, zoom);
		InitializeBaiduSearch();
		//Set Button's OnClick event
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        //Set Spinner's OnClick event
        Spinner searchtype = (Spinner)findViewById(R.id.searchtype);
        searchtype.setOnItemSelectedListener(new OnItemSelectedListener()
        {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				typeid = pos;
				Search(start, end);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) 
			{
			}
        });
        Spinner traffic = (Spinner)findViewById(R.id.traffic);
        traffic.setOnItemSelectedListener(new OnItemSelectedListener()
        {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				trafficid = pos;
				Search(start, end);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) 
			{
			}
        });
		//Start default route search
		mksearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);
		Search(start, end);
	}
	
    void InitializeBaiduMap(GeoPoint center, int zoom)
    {
        bmap = new BMapManager(getApplication());
        bmap.init(null);
        setContentView(R.layout.route);
        mapview = (MapView) findViewById(R.id.bmapsView);
        mapcontrol = mapview.getController();
        mapcontrol.setCenter(center);
        mapcontrol.setZoom(zoom);
    }
    
    void InitializeBaiduSearch()
    {
    	mksearch = new MKSearch();
    	mksearch.init(bmap, new MKSearchListener() 
    	{
			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error) 
			{
				if (error == MKEvent.ERROR_ROUTE_ADDR)
					return;
				if (error != 0 || res == null) 
					return;
				//Add route overlay
				RouteOverlay routeOverlay = new RouteOverlay(Route_Activity.this, mapview);
		        routeOverlay.setData(res.getPlan(0).getRoute(0));
		        mapview.getOverlays().clear();
		        mapview.getOverlays().add(routeOverlay);
		        mapview.getController().zoomToSpan(routeOverlay.getLatSpanE6(), routeOverlay.getLonSpanE6());
		        mapview.getController().animateTo(res.getStart().pt);
		        mapview.refresh();
			}
			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error) 
			{
				if (error == MKEvent.ERROR_ROUTE_ADDR)
					return;
				if (error != 0 || res == null) 
					return;
				//Add route overlay
				RouteOverlay routeOverlay = new RouteOverlay(Route_Activity.this, mapview);
		        routeOverlay.setData(res.getPlan(0).getRoute(0));
		        mapview.getOverlays().clear();
		        mapview.getOverlays().add(routeOverlay);
		        mapview.getController().zoomToSpan(routeOverlay.getLatSpanE6(), routeOverlay.getLonSpanE6());
		        mapview.getController().animateTo(res.getStart().pt);
		        mapview.refresh();
			}
			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult res, int error) 
			{
				if (error == MKEvent.ERROR_ROUTE_ADDR)
					return;
				if (error != 0 || res == null) 
					return;
				//Add transit overlay
				TransitOverlay transitOverlay = new TransitOverlay(Route_Activity.this, mapview);
		        transitOverlay.setData(res.getPlan(0));
		        mapview.getOverlays().clear();
		        mapview.getOverlays().add(transitOverlay);
		        mapview.getController().zoomToSpan(transitOverlay.getLatSpanE6(), transitOverlay.getLonSpanE6());
		        mapview.getController().animateTo(res.getStart().pt);
		        mapview.refresh();
			}
			@Override
			public void onGetAddrResult(MKAddrInfo arg0, int arg1) 
			{
			}
			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) 
			{
			}
			@Override
			public void onGetPoiDetailSearchResult(int arg0, int arg1) 
			{
			}
			@Override
			public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) 
			{
			}
			@Override
			public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1, int arg2) 
			{
			}
			@Override
			public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) 
			{
			}
    	});
    }
    
    public void Search(MKPlanNode start, MKPlanNode end)
    {
    	if (mksearch != null)
    	{
			switch (trafficid)
			{
			case 0:
				switch (typeid)
				{
				case 0:
					mksearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);
					break;
				case 1:
					mksearch.setDrivingPolicy(MKSearch.ECAR_DIS_FIRST);
					break;
				case 2:
					mksearch.setDrivingPolicy(MKSearch.ECAR_FEE_FIRST);
					break;
				}
				mksearch.drivingSearch(null, start, null, end);
				break;
			case 1:
				mksearch.walkingSearch(null, start, null, end);
				break;
			case 2:
				switch (typeid)
				{
				case 0:
					mksearch.setTransitPolicy(MKSearch.EBUS_TIME_FIRST);
					break;
				case 1:
					mksearch.setTransitPolicy(MKSearch.EBUS_WALK_FIRST);
					break;
				case 2:
					mksearch.setTransitPolicy(MKSearch.EBUS_TRANSFER_FIRST);
					break;
				}
				mksearch.transitSearch(city, start, end);
				break;
			}
			Toast.makeText(getApplication(), "正在搜索路线", Toast.LENGTH_SHORT).show();
    	}
    }
    
    @Override
    protected void onDestroy()
    {
        mapview.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        mapview.onPause();
        if (bmap != null)
            bmap.stop();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        mapview.onResume();
        if (bmap != null)
            bmap.start();
        super.onResume();

    }

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.cancel:
				finish();
				break;
			default:
				break;
		}
	}
}
