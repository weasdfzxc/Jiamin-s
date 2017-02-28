package com.marauder;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;





// BaiduMap API
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapStatus;
import com.baidu.mapapi.map.MKMapStatusChangeListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
// BaiduLocation API
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class Main_Activity extends Activity implements BDLocationListener,OnClickListener
{
	//Constant
	public final static int ZOOM_LEVEL = 12;
	//public final static int CENTER_LA = 39930375;
	//public final static int CENTER_LO = 119558981;
	public final static int CENTER_LA = 38048617;
	public final static int CENTER_LO = 114521363;
	public final static int NOTIFY_TIP = 0;
	public final static int NOTIFY_MESSAGE = 1;
	//Some setting values
	public String username = "";
	public String city = "";
	public float lastzoom = 0;
	public GeoPoint lastpos = null;
	//The basic components
    BMapManager bmap = null;
    MapView mapview = null;
    MapController mapcontrol = null;
    //Use for positioning
    LocationClient locationclient = null;
    //My location
    GeoPoint mylocation = null;
    MyLocationOverlay mylocationoverlay = null;
    //The Sets of other overlay
    OverlaySets overlays = null;
    //Other overlay's updater
    OverlayUpdater overlayupdater = null;
    //Check if map center is animated to my location
    boolean animated = false;
    //Use for Updating message
    MessageUpdater messageupdater = null;
    //Record the latest message date you readed
    int latestmid = 0;
    //Just for thread-cross process
    Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Get data from last activity
        Intent data = getIntent();
        username = data.getStringExtra("userName");
        city = data.getStringExtra("city");
        lastzoom = data.getIntExtra("zoom", ZOOM_LEVEL);
		lastpos = new GeoPoint(data.getIntExtra("centerLA", CENTER_LA), data.getIntExtra("centerLO", CENTER_LO));
        //Initialize
        InitializeBaiduMap(lastpos, lastzoom);
        InitializeBaiduLocation();
        //Set TextView to show the user's name
        TextView tx = (TextView) findViewById(R.id.username);
        tx.setText(username);
        //Set View's OnClick event
        TextView notification = (TextView)findViewById(R.id.message);
        notification.setOnClickListener(this);
        Button release = (Button)findViewById(R.id.info);
        release.setOnClickListener(this);
        Button viewinfo = (Button)findViewById(R.id.viewinfo);
        viewinfo.setOnClickListener(this);
        Button fresh = (Button)findViewById(R.id.fresh);
        fresh.setOnClickListener(this);
        //Create sets of other overlays and add to map
        overlays = new OverlaySets(this, getResources(), mapview, GetLayoutView(R.layout.pop), GetLayoutView(R.layout.popup));
        //Use for updating other overlays
        overlayupdater = new OverlayUpdater(this, mapview, getApplicationContext(), overlays);
        //Use for updating new message
        messageupdater = new MessageUpdater(this);
        //Set the map's listener
        MKMapStatusChangeListener listener = new MKMapStatusChangeListener() 
        {  
        	public void onMapStatusChange(MKMapStatus mapStatus) 
        	{  
        		float zoom = mapStatus.zoom;
        		//Control the display of pop(if the map's zoom is big enough or not)
        		if (zoom != lastzoom)
        		{
            		if (lastzoom <= 14.5 && zoom > 14.5)
            			overlayupdater.ShowPop();
            		else if (lastzoom > 14.5 && zoom <= 14.5)
            			overlayupdater.HidePop();
        		}
        		lastzoom = zoom;
        		lastpos = mapStatus.targetGeo;
            }  
        };
        mapview.regMapStatusChangeListener(listener);
        // Add my location overlay
        mylocationoverlay = new MyLocationOverlay(mapview);
        mylocationoverlay.enableCompass();
        mapview.getOverlays().add(mylocationoverlay);
        //Start work
        StartLocation();
        overlayupdater.UpdateKpLocation();
        MessageUpdatedListener mlistner = new MessageUpdatedListener()
        {
        	@Override
			public void GetMessage(final JSONArray data, final int pagenum)
			{
        		try 
        		{
        			//Get the latest message id
					latestmid = data.getJSONObject(data.length() - 1).getInt("mid");
				} 
        		catch (JSONException e) 
        		{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        };
        messageupdater.Update(mlistner, 1, null);
        mapview.refresh();
    }
    
    public GeoPoint GetMyLocation()
    {
    	if (mylocationoverlay != null)
    	{
    		Log.d("debug", "GetMyLocation succeed!");
    		return mylocation;
    	}
    	else
    		return null;
    }
    
	View GetLayoutView(int layout_id)
	{
		LayoutInflater inflator = getLayoutInflater();
		return inflator.inflate(layout_id, null);
	}

    void InitializeBaiduMap(GeoPoint center, float zoom)
    {
        bmap = new BMapManager(getApplication());
        bmap.init(null);
        setContentView(R.layout.main);
        mapview = (MapView) findViewById(R.id.bmapsView);
        mapcontrol = mapview.getController();
        mapcontrol.setCenter(center);
        mapcontrol.setZoom(zoom);
    }
    
    void InitializeBaiduLocation()
    {
        locationclient = new LocationClient(getApplication());
        locationclient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        //High Accuracy means using both Web and GPS at the same time
        option.setLocationMode(LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        //Wait 2000ms per time to update your position
        option.setScanSpan(2000);
        locationclient.setLocOption(option);
    }
    
    void StartLocation()
    {
    	if (locationclient != null)
    		locationclient.start();
    }
    
    public void SetNotify(final int type, final String text)
    {
    	final Runnable uiupdater = new Runnable()
    	{
    		public void run()
    		{
    			TextView view = null;
    			switch (type)
    			{
    			case NOTIFY_TIP:
        	    	view = (TextView)findViewById(R.id.tip);
    				break;
    			case NOTIFY_MESSAGE:
        	    	view = (TextView)findViewById(R.id.message);
    				break;
    			}
    	    	view.setText(text.toCharArray(), 0, text.length());
    	    	view.setVisibility(View.VISIBLE);
    		}
    	};
    	new Thread ()
    	{
    		public void run()
    		{
    			handler.post(uiupdater);
    		}
    	}.start();
    }
    
    public void CloseNotify(final int type)
    {
    	final Runnable uiupdater = new Runnable()
    	{
    		public void run()
    		{
    			TextView view = null;
    			switch (type)
    			{
    			case NOTIFY_TIP:
        	    	view = (TextView)findViewById(R.id.tip);
    				break;
    			case NOTIFY_MESSAGE:
        	    	view = (TextView)findViewById(R.id.message);
    				break;
    			}
    	    	view.setVisibility(View.INVISIBLE);
    		}
    	};
    	new Thread ()
    	{
    		public void run()
    		{
    			handler.post(uiupdater);
    		}
    	}.start();
    }
    
    
    
    @Override
    public void onReceiveLocation(BDLocation location)
    {
        // Update my location
        if (location == null)
            return;
        LocationData locdata = new LocationData();
        locdata.latitude = location.getLatitude();
        locdata.longitude = location.getLongitude();
        locdata.direction = location.getDirection();
        mylocation = new GeoPoint((int) (locdata.latitude * 1E6), (int) (locdata.longitude * 1E6));
        if (!animated && !(mylocation.getLatitudeE6() == 0 || mylocation.getLongitudeE6() == 0))
        {
        	mapview.getController().animateTo(mylocation);
        	animated = true;
        }
        if (mylocationoverlay != null)
            mylocationoverlay.setData(locdata);
    }

    @Override
    public void onReceivePoi(BDLocation location)
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    protected void onDestroy()
    {
        mapview.destroy();
        if (bmap != null)
        {
            bmap.destroy();
            bmap = null;
        }
        super.onDestroy();
    }

    public void PauseMap()
    {
    	mapview.onPause();
        if (bmap != null)
            bmap.stop();
    }
    
    @Override
    protected void onPause()
    {
    	PauseMap();
        super.onPause();
    }
    
    public void ResumeMap()
    {
        mapview.onResume();
        if (bmap != null)
            bmap.start();
    }

    @Override
    protected void onResume()
    {
    	ResumeMap();
        super.onResume();
    }
    
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 创建退出对话框  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 设置对话框标题  
            isExit.setTitle("科普云游");  
            // 设置对话框消息  
            isExit.setMessage("确定要退出吗");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };    
    
    @Override
    public void onClick(View v)
    {
    	Intent intent = null;
        switch (v.getId())
        {
        	case R.id.fresh:
        		SetNotify(NOTIFY_MESSAGE, "正在加载最新活动信息...");
        		RotateAnimation animation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
        		animation.setDuration(1000);
        		v.setAnimation(animation);
        		v.startAnimation(animation);
        		MessageUpdatedListener listener = new MessageUpdatedListener()
        		{
        			@Override
        			public void GetMessage(final JSONArray data, final int pagenum)
        			{
        				try 
        				{
        					//Compare the latest message id to determine if notify
							int mid = data.getJSONObject(data.length() - 1).getInt("mid");
							if (latestmid < mid)
							{
								SetNotify(NOTIFY_MESSAGE, "有新的活动信息等待查看");
								latestmid = mid;
							}
							else
								CloseNotify(NOTIFY_MESSAGE);
						} 
        				catch (JSONException e) 
        				{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        		};
        		messageupdater.Update(listener, 1, null);
        	break;
        	case R.id.message:
        		CloseNotify(NOTIFY_MESSAGE);
        		intent = new Intent(this, Message_Activity.class);
        		startActivity(intent);
        		break;
        	case R.id.viewinfo:
        		CloseNotify(NOTIFY_MESSAGE);
        		intent = new Intent(this, Message_Activity.class);
        		startActivity(intent);
        		break;
            case R.id.info:
                intent = new Intent(this, Release_Activity.class);
                String s = String.valueOf(mylocationoverlay.getMyLocation().latitude) + ","
                		+ String.valueOf(mylocationoverlay.getMyLocation().longitude);
                intent.putExtra("location", s);
                String name = getIntent().getExtras().getString("userName");
                intent.putExtra("userName", name);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
