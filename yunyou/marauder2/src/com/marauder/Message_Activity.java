package com.marauder;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Message_Activity extends Activity implements OnClickListener,MessageUpdatedListener
{
	//Use for Updating message
	MessageUpdater updater = null;
	//Just for thread-cross process
    Handler handler = new Handler();
    //Data list items
    ArrayList<HashMap<String, Object>> listitems = null;
    //Data list's footerview
    View loadmore = null;
    //Record current page index
    int pageindex = 1;
    //Specified message source
    String source = null;
	//Message data cache
	JSONArray cache = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//Determine if get specified message
		Intent intent = getIntent();
		source = intent.getStringExtra("name");
		setContentView(R.layout.message);
		//Set Button's OnClick event
        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
		//Set ListView's footerview
		ListView list = (ListView)findViewById(R.id.meglist);
		loadmore = getLayoutInflater().inflate(R.layout.loadmore, null);
		TextView tip = (TextView)loadmore.findViewById(R.id.tip);
		tip.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view) 
			{
				TextView tip = (TextView)view;
				tip.setClickable(false);
				tip.setText("正在加载中...");
				pageindex++;
				//Update when the footerview tapped
				Update(pageindex, source);
			}
			
		});
		list.addFooterView(loadmore);
		//When item be clicked
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) 
			{
				if (pos == -1 || pos > cache.length() - 1) return;
				try 
				{
					//Send nessesary data
					Intent data = new Intent(Message_Activity.this, Content_Activity.class);
					data.putExtra("mid", cache.getJSONObject(pos).getString("mid"));
					data.putExtra("title", cache.getJSONObject(pos).getString("title"));
					data.putExtra("name", cache.getJSONObject(pos).getString("name"));
					data.putExtra("date", cache.getJSONObject(pos).getString("startdate"));
					data.putExtra("content", cache.getJSONObject(pos).getString("content"));
					startActivity(data);
				} 
				catch (JSONException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        //Initialize data list
        listitems = new ArrayList<HashMap<String, Object>>();
        //Initialize cache
        cache = new JSONArray();
        //Update message list
        updater = new MessageUpdater(getApplication());
        Update(pageindex, source);
	}
	
	public void Update(int pageindex, String source)
	{
		if (updater != null)
			updater.Update(this, pageindex, source);
	}
	
	@Override
	public void GetMessage(final JSONArray data, final int pagenum)
	{
		final Runnable uiupdater = new Runnable()
		{
			@Override
			public void run() 
			{
				try
				{
					//Set ListView's adapter to show message list
					ListView list = (ListView)findViewById(R.id.meglist);
					//Build data
					for (int i = 0;i < data.length();++i)
					{
						HashMap<String ,Object> map = new HashMap<String, Object>();
						map.put("title", data.getJSONObject(i).getString("title"));
						map.put("name", data.getJSONObject(i).getString("name"));
						String date = "时间：";
						date += data.getJSONObject(i).getString("begdate").split("T")[0];
						date += " ～ ";
						date += data.getJSONObject(i).getString("enddate").split("T")[0];
						map.put("date", date);
						listitems.add(map);
						//Add message to cache
						cache.put(data.getJSONObject(i));
					}
					list.setAdapter(new SimpleAdapter(getApplication(), listitems, R.layout.listitem, 
							new String[] {"title", "name", "date"},
							new int[] {R.id.title, R.id.name, R.id.date}));
					//Judge if the page is last one
					if (pageindex == pagenum)
					{
						//Update footerview
						TextView tip = (TextView)loadmore.findViewById(R.id.tip);
						tip.setClickable(false);
						tip.setText("没有更多活动信息了");
					}
					else
					{
						//Update footerview
						TextView tip = (TextView)loadmore.findViewById(R.id.tip);
						tip.setClickable(true);
						tip.setText("点击加载更多");
					}
				}
				catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
		};
		new Thread()
		{
			public void run()
			{
				handler.post(uiupdater);
			}
		}.start();
	}

	@Override
	public void onClick(View v) 
	{
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
