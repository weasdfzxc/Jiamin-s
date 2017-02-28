package com.marauder;

import android.os.Bundle;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/* Update message of activities */
public class MessageUpdater 
{
	//the index of message page
	int pageindex = 1;
	//Thread for getting message
	HttpThread message = null;
	//Use for notification 
	MessageUpdatedListener listener = null;
	
	
	public MessageUpdater(final Context context)
	{
		message = new HttpThread();
		message.setUrl(context.getString(R.string.main_url) + "release-json");
		message.setHandler(new Handler()
		{
			public void handleMessage(Message message)
            {
                try
                {
                	//Get data
                	JSONObject responseJson = new JSONObject(message.obj.toString());
                	JSONArray data = responseJson.getJSONArray("messages");
                	int pagenum = responseJson.getInt("pageNum");
                	if (listener != null)
                		listener.GetMessage(data, pagenum);
                }
                catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (NullPointerException e)
                {
                	Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                }
            }
		});
	}
	
	public void Update(MessageUpdatedListener listener, int pageindex, String source)
	{
		this.listener = listener;
		this.pageindex = pageindex;
		Bundle params = new Bundle();
		params.putString("pageIndex", String.valueOf(pageindex));
		if (source != null) {
			params.putString("kpname", source);
		Log.d("JSON", source);}
		message.setData(params);
		Thread thread = new Thread(message);
		thread.start();
	}
}
