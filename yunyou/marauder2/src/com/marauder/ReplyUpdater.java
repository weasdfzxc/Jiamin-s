package com.marauder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ReplyUpdater
{
    // the index of message page
    int pageindex = 1;
    HttpThread message = null;
    Thread thread = null;
    // Use for notification
    ReplyUpdatedListener listener = null;

    public ReplyUpdater(Context context)
    {
        message = new HttpThread();
        message.setUrl(context.getString(R.string.main_url) + "reply-json");
        message.setHandler(new Handler() {
            public void handleMessage(Message message)
            {
                try
                {
                    // Get data
                    
                    JSONObject responseJson = new JSONObject(
                                                             message.obj.toString());
                    
                    JSONArray data = responseJson.getJSONArray("replies");
                    Log.d("JSON",data.toString());
                    int pagenum = responseJson.getInt("pageNum");
                    
                    if (listener != null)
                        listener.GetReply(data, pagenum);
                    thread.interrupt();
                }
                catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    public void Update(ReplyUpdatedListener listener, int pageindex,
            Bundle bundle)
    {
        this.listener = listener;
        Bundle params = null;
        if (bundle != null)
            params = bundle;
        params.putString("pageIndex", String.valueOf(pageindex));
        
        message.setData(params);
        thread = new Thread(message);
        thread.start();
        
    }
}
